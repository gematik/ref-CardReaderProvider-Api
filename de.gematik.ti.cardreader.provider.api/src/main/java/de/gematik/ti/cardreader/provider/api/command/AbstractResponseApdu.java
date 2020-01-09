/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.command;

import java.util.Arrays;

/**
 * APDU Response
 *
 */
public class AbstractResponseApdu implements IResponseApdu {
    private final byte[] apdu;

    AbstractResponseApdu(final byte[] apdu) {
        if (apdu.length < 2) {
            throw new IllegalArgumentException("Response APDU must not have less than 2 bytes (status bytes SW1, SW2)");
        }

        this.apdu = Arrays.copyOf(apdu, apdu.length);
    }

    /**
     * Get the response body length
     * @return response body length
     */
    @Override
    public int getNr() {
        return apdu.length - 2;
    }

    /**
     * Get the response data
     * @return response data
     */
    @Override
    public byte[] getData() {
        return Arrays.copyOf(apdu, apdu.length - 2);
    }

    /**
     * Get the response status word - upper byte
     * @return response status word - upper byte
     */
    @Override
    public int getSW1() {
        return apdu[apdu.length - 2] & 0xFF;
    }

    /**
     * Get the response status word - lower byte
     * @return response status word - lower byte
     */
    @Override
    public int getSW2() {
        return apdu[apdu.length - 1] & 0xFF;
    }

    /**
     * Get the response status word
     * @return response status word
     */
    @Override
    public int getSW() {
        return getSW1() << 8 | getSW2();
    }

    /**
     * Get the raw response
     * @return raw response
     */
    @Override
    public byte[] getBytes() {
        return Arrays.copyOf(apdu, apdu.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractResponseApdu)) {
            return false;
        }
        AbstractResponseApdu that = (AbstractResponseApdu) o;
        return Arrays.equals(apdu, that.apdu);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(apdu);
    }
}
