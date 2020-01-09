/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.card;

/**
 * SmartCard communication protocol representation
 *
 */
public enum CardProtocol {
    T0(0),
    T1(1),
    T15(15); // NOCS(hoa): literal constant just for bit calculation

    private final int rawValue;

    CardProtocol(final int rawValue) {
        this.rawValue = rawValue;
    }

    /**
     * Representing int value of the enum
     * @return
     */
    public int getRawValue() {
        return rawValue;
    }

    /**
     * Calculate enum value from representing int value
     * @param rawValue representing int value
     * @return enum value or if no match null
     */
    public static CardProtocol fromInt(final int rawValue) {
        for (CardProtocol proto : values()) {
            if ((rawValue & 0x0F) == proto.rawValue) {
                return proto;
            }
        }
        return null;
    }
}
