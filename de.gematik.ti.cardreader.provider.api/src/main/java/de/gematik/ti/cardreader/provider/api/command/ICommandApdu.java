/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.command;

/**
 * A command application protocol data unit (A-PDU) according to ISO7816-4.
 * The structure follows those described in ISO7816-4 with the exception of command length
 * element. Command length (Nc) and corresponding encoded value (lc) is derived
 * from command data element and pasted into command's byte array.
 *
 *  For the header bytes cla, ins, p1, and p2 the Java type `int`
 *  is used while for the header an APDU an unsigned octet is used. Constructors will take
 *  only the least significant byte 8 `int` value.
 *
 *  Instances of this class are immutable. Where data is passed in or out
 *  via byte arrays, defensive cloning is performed.
 *
 */
public interface ICommandApdu {
    /**
     *  Returns the value of class byte CLA of this command APDU
     *
     * @return value class byte CLA
     */
    int getCla();

    /**
     * Returns the value of instruction byte INS of this command APDU
     *
     * @return value of instruction byte INS
     */
    int getIns();

    /**
     *  Returns the value of parameter byte P1 of this command APDU
     *
     * @return value of parameter byte P1
     */
    int getP1();

    /**
     *  Returns the value of parameter byte P2 of this command APDU
     *
     * @return value of parameter byte P2
     */
    int getP2();

    /**
     *  Returns the number of data bytes in command APDU or 0 if there aren't any data in it.
     *
     * @return number of data bytes
     */
    int getNc();

    /**
     * Returns a copy of the data bytes in the command body. If this APDU as
     * no body, this method returns a byte array with length zero.
     *
     * @return a copy of the data bytes in the command body or the empty
     *    byte array if this command APDU has no body.
     */
    byte[] getData();

    /**
     * Returns the number of expected data in response APDU. This number is sent within this command APDU.
     * If any number of available data is expected, the value is either {@linkplain AbstractCommandApdu#EXPECTED_LENGTH_WILDCARD_SHORT} or
     * {@linkplain AbstractCommandApdu#EXPECTED_LENGTH_WILDCARD_EXTENDED}
     * If value is set 0, number of expected data is either {@linkplain AbstractCommandApdu#EXPECTED_LENGTH_WILDCARD_SHORT} or
     * {@linkplain AbstractCommandApdu#EXPECTED_LENGTH_WILDCARD_EXTENDED} is depending on the length of data according to ISO 7816-3.
     * If there is no data expected in response APDU, e.g. for case 1 or 3 APDU, this value is set to `null`.
     *
     * @return number of expected data
     */
    Integer getNe();

    /**
     *  returns a copy of the APDU octet string representation
     *
     * @return byte array of APDU octet string representation
     */
    byte[] getBytes();
}
