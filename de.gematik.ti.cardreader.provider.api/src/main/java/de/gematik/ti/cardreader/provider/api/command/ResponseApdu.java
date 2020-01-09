/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.command;

/**
 * APDU Response
 *
 */
public class ResponseApdu extends AbstractResponseApdu {
    /**
     * Create Instance
     * @param apdu
     */
    public ResponseApdu(final byte[] apdu) {
        super(apdu);
    }

}
