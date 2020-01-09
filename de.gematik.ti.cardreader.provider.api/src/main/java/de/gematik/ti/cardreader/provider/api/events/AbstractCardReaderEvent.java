/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.events;

import de.gematik.ti.cardreader.provider.api.ICardReader;

/**
 * Abstract Event inform about card reader events
 *
 */
public abstract class AbstractCardReaderEvent {

    private final ICardReader cardReader;

    protected AbstractCardReaderEvent(final ICardReader cardReader) {
        this.cardReader = cardReader;
    }

    /**
     * Return the card reader object
     * @return card reader object
     */
    public ICardReader getCardReader() {
        return cardReader;
    }
}
