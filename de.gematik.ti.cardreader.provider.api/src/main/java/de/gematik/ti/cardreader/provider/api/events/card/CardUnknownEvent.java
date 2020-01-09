/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.events.card;

import de.gematik.ti.cardreader.provider.api.ICardReader;
import de.gematik.ti.cardreader.provider.api.events.AbstractCardReaderEvent;

/**
 * This Event inform about unknown card connected to card reader
 *
 */
public class CardUnknownEvent extends AbstractCardReaderEvent {

    /**
     * Create a Eventobject to inform about unknown connected card to card reader
     *
     * @param cardReader - card reader with connected card
     */
    public CardUnknownEvent(final ICardReader cardReader) {
        super(cardReader);
    }
}
