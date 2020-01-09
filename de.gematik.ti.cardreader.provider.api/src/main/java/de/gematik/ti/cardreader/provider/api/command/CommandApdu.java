/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.command;

/**
 * Command APDU Object
 *
 */
public class CommandApdu extends AbstractCommandApdu {
    /**
     * Constructs a case 1 command APDU just from the 4 header bytes
     * @param cla class octet of command APDU
     * @param ins INS octet of command APDU
     * @param p1 P1 octet of command APDU
     * @param p2 P2 octet of command APDU
     */
    public CommandApdu(final int cla, final int ins, final int p1, final int p2) {
        this(cla, ins, p1, p2, null, null);
    }

    /**
     * Constructs a case 2 APDU
     * If ne is `null` it is encoded as case 1 APDU
     *
     * @param cla class octet of command APDU
     * @param ins INS octet of command APDU
     * @param p1 P1 octet of command APDU
     * @param p2 P2 octet of command APDU
     * @param ne number of expected length
     */
    public CommandApdu(final int cla, final int ins, final int p1, final int p2, final Integer ne) {
        this(cla, ins, p1, p2, null, ne);
    }

    /**
     * Constructs a case 3 APDU
     * If data is `null` or empty, it constructs a case 1 APDU. Command data length Nc is derived from the
     * byte array of data param
     *
     * @param cla class octet of command APDU
     * @param ins INS octet of command APDU
     * @param p1 P1 octet of command APDU
     * @param p2 P2 octet of command APDU
     * @param data command data
     */
    public CommandApdu(final int cla, final int ins, final int p1, final int p2, final byte[] data) {
        this(cla, ins, p1, p2, data, null);
    }

    /**
     * Constructs a case 4 APDU from the four header bytes, command data,
     * and expected response data length.  Command data length Nc is derived from the
     * byte array of data param
     * 
     * If data is empty or `null` and/or ne is `null`, APDU is encoded as case 1, 2, or 3 per ISO 7816 respectively.
     * 
     *
     * @param cla CLA byte
     * @param ins INS byte
     * @param p1 P1 byte
     * @param p2 P2 byte
     * @param data data octet string
     *        if data is `null` it is encoded as case 1 or 3 respectively
     * @param ne number of expected bytes in response.
     *        if ne is `null` it is encoded as case 1 or 3 respectively
     *        ne = 0 is encoded as wildcard (short or extended)
     *        ne = AbstractApdu.EXPECTED_LENGTH_WILDCARD_SHORT or ne = AbstractApdu.EXPECTED_LENGTH_WILDCARD_EXTENDED it is encoded as
     *           short or extended wildcard respectively
     * @throws IllegalArgumentException  thrown in case of invalid parameter values
     */
    public CommandApdu(final int cla, final int ins, final int p1, final int p2, final byte[] data, final Integer ne) {
        if (ne != null && (ne > EXPECTED_LENGTH_WILDCARD_EXTENDED || ne < 0)) {
            throw new IllegalArgumentException(
                    "expected command response length ne out of bounds, must not be less than 0 and not more than " + EXPECTED_LENGTH_WILDCARD_EXTENDED);
        }

        setHeader(cla, ins, p1, p2);
        setData(data);
        setNe(ne);
    }
}
