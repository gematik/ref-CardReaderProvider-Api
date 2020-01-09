/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.events.card;

import de.gematik.ti.cardreader.provider.api.ICardReader;
import de.gematik.ti.cardreader.provider.api.events.AbstractCardReaderEvent;

/**
 * This Event inform about new card present on card reader
 *
 */
public class CardPresentEvent extends AbstractCardReaderEvent {

    /**
     * Create a Eventobject to inform about new present card on card reader
     *
     * @param cardReader - card reader with present card
     */
    public CardPresentEvent(final ICardReader cardReader) {
        super(cardReader);
    }
}
