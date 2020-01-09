/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.card;

/**
 * Card failures exception
 *
 */
public class CardException extends Exception {

    /**
     * Create new instance with custom message
     * @param message message for exception
     */
    public CardException(final String message) {
        super(message);
    }

    /**
     * Create new instance with custom message and previous exception
     * @param message message for exception
     * @param exception rethrown exception
     */
    public CardException(final String message, final Exception exception) {
        super(message, exception);
    }
}
