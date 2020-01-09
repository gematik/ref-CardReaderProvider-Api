/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.events.card;

import de.gematik.ti.cardreader.provider.api.ICardReader;
import de.gematik.ti.cardreader.provider.api.events.AbstractCardReaderEvent;

/**
 * This Event inform about absent card from card reader
 *
 */
public class CardAbsentEvent extends AbstractCardReaderEvent {

    /**
     * Create a Eventobject to inform about absent card from card reader
     *
     * @param cardReader - card reader with absent card
     */
    public CardAbsentEvent(final ICardReader cardReader) {
        super(cardReader);
    }
}
