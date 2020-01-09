/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api;

import org.greenrobot.eventbus.EventBus;

import de.gematik.ti.cardreader.provider.api.events.CardReaderConnectedEvent;
import de.gematik.ti.cardreader.provider.api.events.CardReaderDisconnectedEvent;
import de.gematik.ti.cardreader.provider.api.listener.InitializationStatus;

/**
 * include::{userguide}/CRPAPI_Overview.adoc[tag=CardReaderController]
 *
 */
public abstract class AbstractCardReaderController implements ICardReaderController {

    protected void informAboutReaderConnection(final ICardReader cardReader, final InitializationStatus initStatus) {
        EventBus.getDefault().post(new CardReaderConnectedEvent(cardReader, initStatus));
    }

    protected void informAboutReaderDisconnection(final ICardReader cardReader) {
        EventBus.getDefault().post(new CardReaderDisconnectedEvent(cardReader));
    }

    public CardEventTransmitter createCardEventTransmitter(ICardReader cardReader) {
        return new CardEventTransmitter(cardReader);
    }
}
