/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.command;

/**
 * Represent an APDU
 *
 */
public abstract class AbstractApdu {
    static final int BYTE_MASK = 0xFF;

    /**
     * Value for when wildcardShort for expected length encoding is needed
     */
    public static final int EXPECTED_LENGTH_WILDCARD_SHORT = 256;

    /**
     * Value for when wildcardExtended for expected length encoding is needed
     */
    public static final int EXPECTED_LENGTH_WILDCARD_EXTENDED = 65536;

    static byte[] encodeDataLengthExtended(final int nc) {
        byte[] lengthData = new byte[3];
        lengthData[1] = (byte) (nc >> 8);
        lengthData[2] = (byte) (nc & BYTE_MASK);

        return lengthData;
    }

    static byte[] encodeDataLengthShort(final int nc) {
        byte[] lengthData = new byte[1];
        lengthData[0] = (byte) nc;
        return lengthData;
    }

    static byte[] encodeExpectedLengthExtended(final int ne) {
        byte[] lengthData = new byte[2];

        if (ne != EXPECTED_LENGTH_WILDCARD_EXTENDED) { // == 65536
            lengthData[0] = (byte) (ne >> 8);
            lengthData[1] = (byte) (ne & 0xff);
        }
        return lengthData;
    }

    static byte[] encodeExpectedLengthShort(final int ne) {
        byte[] lengthData = new byte[1];
        if (ne != EXPECTED_LENGTH_WILDCARD_SHORT) {
            lengthData[0] = (byte) ne;
        }
        return lengthData;
    }
}
