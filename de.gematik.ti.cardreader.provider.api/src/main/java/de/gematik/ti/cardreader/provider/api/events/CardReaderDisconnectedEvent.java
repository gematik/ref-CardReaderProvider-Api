/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.events;

import de.gematik.ti.cardreader.provider.api.ICardReader;

/**
 * This Event inform about new card reader disconnection
 *
 */
public class CardReaderDisconnectedEvent extends AbstractCardReaderEvent {

    /**
     * Create a Eventobject to inform about disconnected card reader
     *
     * @param cardReader - disconnected card reader
     */
    public CardReaderDisconnectedEvent(final ICardReader cardReader) {
        super(cardReader);
    }

}
