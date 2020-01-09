/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.events.card;

import de.gematik.ti.cardreader.provider.api.ICardReader;
import de.gematik.ti.cardreader.provider.api.events.AbstractCardReaderEvent;

/**
 * This Event inform about card timeout
 *
 */
public class CardTimeoutEvent extends AbstractCardReaderEvent {

    /**
     * Create a Eventobject to inform about card timeout
     *
     * @param cardReader - card reader with connected card
     */
    public CardTimeoutEvent(final ICardReader cardReader) {
        super(cardReader);
    }
}
