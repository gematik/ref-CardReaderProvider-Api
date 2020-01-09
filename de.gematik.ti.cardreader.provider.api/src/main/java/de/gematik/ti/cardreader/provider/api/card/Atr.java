/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.card;

/**
 * Answer to Reset
 *
 */
public class Atr {
    private final byte[] atrRaw;

    /**
     * Create instance with byte array
     * @param atrRaw
     */
    public Atr(final byte[] atrRaw) {
        this.atrRaw = atrRaw.clone();
    }

    /**
     * Returns a copy of the ATR as byte array
     *
     * @return a copy of the ATR as byte array
     */
    public byte[] getBytes() {
        return atrRaw.clone();
    }
}
