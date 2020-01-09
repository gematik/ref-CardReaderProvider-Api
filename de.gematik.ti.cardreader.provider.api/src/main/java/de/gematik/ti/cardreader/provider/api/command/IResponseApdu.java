/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.command;

public interface IResponseApdu {
    /**
     * Returns the number of data bytes in the body of response APDU or 0 if response
     * APDU has no body. This call is equivalent to <code>getData().length</code>.
     *
     * @return the number of data bytes in the body of response APDU
     */
    int getNr();

    /**
     * Returns a copy of the data bytes in body of response APDU. If response APDU has
     * no body, this method returns a byte array with a length of zero.
     *
     * @return a copy of the data bytes in body of response APDU body or an empty
     *         byte array if response APDU has no body
     */
    byte[] getData();

    /**
     * Returns the value of the status byte SW1 of response APPDU.
     * It returns a value of an unsigned byte in range of 0 to 255.
     *
     * @return value of the status byte SW1
     */
    public int getSW1();

    /**
     * Returns the value of the status byte SW2 of response APPDU.
     * It returns a value of an unsigned byte in range of 0 to 255.
     *
     * @return value of the status byte SW2
     */
    public int getSW2();

    /**
     * Returns the value of the status bytes SW1 and SW2 as a single
     * status word SW.
     * It is defined as
     * {@code (getSW1() << 8) | getSW2()}
     *
     * @return the value of the status word SW.
     */
    public int getSW();

    /**
     * Returns a copy of the complete bytes in response APDU.
     *
     * @return a copy of the complete bytes in response APDU.
     */
    public byte[] getBytes();
}
