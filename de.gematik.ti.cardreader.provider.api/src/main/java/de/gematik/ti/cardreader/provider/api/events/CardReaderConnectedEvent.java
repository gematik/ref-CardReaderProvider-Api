/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.events;

import de.gematik.ti.cardreader.provider.api.ICardReader;
import de.gematik.ti.cardreader.provider.api.listener.InitializationStatus;

/**
 * This Event inform about new card reader connection
 *
 */
public class CardReaderConnectedEvent extends AbstractCardReaderEvent {

    private final InitializationStatus initStatus;

    /**
     * Create a Eventobject to inform about new connected card reader and initialisation status
     *
     * @param cardReader - object for connected card reader
     * @param initStatus - initialisation status of card reader
     */
    public CardReaderConnectedEvent(final ICardReader cardReader, final InitializationStatus initStatus) {
        super(cardReader);
        this.initStatus = initStatus;
    }

    /**
     * Return the initialisation status of the connected card reader
     * @return Initialization Status 
     */
    public InitializationStatus getInitStatus() {
        return initStatus;
    }
}
