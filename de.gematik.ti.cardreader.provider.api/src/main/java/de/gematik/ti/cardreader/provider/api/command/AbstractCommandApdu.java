/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.command;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 An APDU Command per ISO/IEC 7816-4.
 Command APDU encoding options:

 ```
 case 1:  |CLA|INS|P1 |P2 |                                 len = 4
 case 2s: |CLA|INS|P1 |P2 |LE |                             len = 5
 case 3s: |CLA|INS|P1 |P2 |LC |...BODY...|                  len = 6..260
 case 4s: |CLA|INS|P1 |P2 |LC |...BODY...|LE |              len = 7..261
 case 2e: |CLA|INS|P1 |P2 |00 |LE1|LE2|                     len = 7
 case 3e: |CLA|INS|P1 |P2 |00 |LC1|LC2|...BODY...|          len = 8..65542
 case 4e: |CLA|INS|P1 |P2 |00 |LC1|LC2|...BODY...|LE1|LE2|  len =10..65544

 LE, LE1, LE2 may be 0x00.
 LC must not be 0x00 and LC1|LC2 must not be 0x00|0x00
 ```
 */
public abstract class AbstractCommandApdu extends AbstractApdu implements ICommandApdu {
    private final byte[] header = new byte[4];
    private byte[] data = new byte[0];
    private Integer ne = null;

    private ByteArrayOutputStream apdu = null;

    private synchronized byte[] getApdu() {
        if (apdu == null) {
            apdu = new ByteArrayOutputStream(header.length + data.length);
        }
        if (apdu.size() == 0) {
            calculate();
        }
        return apdu.toByteArray();
    }

    private void calculate() {
        int nc = data.length;
        apdu.write(header, 0, header.length);
        if (nc > 0) {
            if (ne != null) {
                doCase4sOr4e(nc);
            } else {
                doCase3sOr3e(nc);
            }
        } else {
            // no data, case 1 or 2
            if (ne != null) {
                doCase2sOr2e();
            }
            // else: case 1, just header
        }
    }

    // case 2s or 2e
    private void doCase2sOr2e() {
        final byte[] le;
        if (ne <= EXPECTED_LENGTH_WILDCARD_SHORT) {
            // case 2s
            le = encodeExpectedLengthShort(ne);
        } else {
            // case 2e
            apdu.write(0);
            le = encodeExpectedLengthExtended(ne);
        }
        apdu.write(le, 0, le.length);
    }

    // case 3s or 3e
    private void doCase3sOr3e(final int nc) {
        final byte[] lc;
        if (nc <= 255) {
            lc = encodeDataLengthShort(nc);
        } else {
            lc = encodeDataLengthExtended(nc);
        }
        apdu.write(lc, 0, lc.length);
        apdu.write(data, 0, data.length);
    }

    // case 4s or 4e
    private void doCase4sOr4e(final int nc) {
        final byte[] lc;
        final byte[] le;
        if (nc <= 255 && ne <= EXPECTED_LENGTH_WILDCARD_SHORT) {
            // case 4s
            lc = encodeDataLengthShort(nc);
            le = encodeExpectedLengthShort(ne);
        } else {
            // case 4e
            lc = encodeDataLengthExtended(nc);
            le = encodeExpectedLengthExtended(ne);
        }
        apdu.write(lc, 0, lc.length);
        apdu.write(data, 0, data.length);
        apdu.write(le, 0, le.length);
    }

    protected void setHeader(int cla, int ins, int p1, int p2) {
        if (cla < 0 || ins < 0 || p1 < 0 || p2 < 0) {
            throw new IllegalArgumentException("APDU header fields must not be less than 0");
        }
        if (cla > 0xFF || ins > 0xFF || p1 > 0xFF || p2 > 0xFF) {
            throw new IllegalArgumentException("APDU header fields must not be greater than 255 (0xFF)");
        }

        header[0] = (byte) cla;
        header[1] = (byte) ins;
        header[2] = (byte) p1;
        header[3] = (byte) p2;
        resetApduBuffer();
    }

    protected void setData(final byte[] data) {
        if (data != null) {
            if (data.length > 65535) {
                throw new IllegalArgumentException("cmd data length must not exceed 65535 bytes");
            }
            this.data = Arrays.copyOf(data, data.length);
            resetApduBuffer();
        }
    }

    private void resetApduBuffer() {
        if (apdu != null) {
            apdu.reset();
        }
    }

    protected void setNe(final Integer ne) {
        this.ne = ne;
        resetApduBuffer();
    }

    /**
     * Get the APDU CLA
     * @return APDU CLA
     */
    @Override
    public int getCla() {
        return header[0] & BYTE_MASK;
    }

    /**
     * Get the APDU Instruction
     * @return APDU Instruction
     */
    @Override
    public int getIns() {
        return header[1] & BYTE_MASK;
    }

    /**
     * Get the APDU P1
     * @return APDU P1
     */
    @Override
    public int getP1() {
        return header[2] & BYTE_MASK;
    }

    /**
     * Get the APDU P2
     * @return APDU P2
     */
    @Override
    public int getP2() {
        return header[3] & BYTE_MASK;
    }

    /**
     * Get the APDU Command body length
     * @return APDU Command body length
     */
    @Override
    public int getNc() {
        if (data != null) {
            return data.length;
        } else {
            return 0;
        }
    }

    /**
     * Get the APDU body data
     * @return APDU body data
     */
    @Override
    public byte[] getData() {
        return Arrays.copyOf(this.data, this.data.length);
    }

    /**
     * Get the APDU Expected length in response body
     * @return APDU Expected length in response body
     */
    @Override
    public Integer getNe() {
        return ne;
    }

    /**
     * Get the raw APDU
     * @return raw APDU
     */
    @Override
    public byte[] getBytes() {
        return getApdu();
    }

    @Override
    public String toString() {
        byte[] apduBytes = getBytes();
        return "CommandAPDU: " + apduBytes.length + " bytes, nc=" + data.length + ", ne=" + ne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractCommandApdu)) {
            return false;
        }
        AbstractCommandApdu that = (AbstractCommandApdu) o;
        return Arrays.equals(getApdu(), that.getApdu());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getApdu());
    }
}
