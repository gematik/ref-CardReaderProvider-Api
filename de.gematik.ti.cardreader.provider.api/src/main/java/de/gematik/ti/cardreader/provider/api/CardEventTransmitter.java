/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api;

import org.greenrobot.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gematik.ti.cardreader.provider.api.events.card.CardAbsentEvent;
import de.gematik.ti.cardreader.provider.api.events.card.CardPresentEvent;
import de.gematik.ti.cardreader.provider.api.events.card.CardTimeoutEvent;
import de.gematik.ti.cardreader.provider.api.events.card.CardUnknownEvent;

/**
 * include::{userguide}/CRPAPI_Overview.adoc[tag=CardEventTransmitter]
 *
 */
public class CardEventTransmitter {
    private final ICardReader cardReader;
    private static final Logger LOG = LoggerFactory.getLogger(CardEventTransmitter.class);

    /**
     * Create an new Object for specific card reader
     * @param cardReader
     */
    CardEventTransmitter(final ICardReader cardReader) {
        this.cardReader = cardReader;
    }

    /**
     * Send event to inform about new card present on card reader
     */
    public void informAboutCardPresent() {
        LOG.debug(cardReader.getName() + "-Device: \"card present\"");
        EventBus.getDefault().post(new CardPresentEvent(cardReader));
    }

    /**
     * Send event to inform about card absent on card reader
     */
    public void informAboutCardAbsent() {
        LOG.debug(cardReader.getName() + "-Device: \"card absent\"");
        EventBus.getDefault().post(new CardAbsentEvent(cardReader));
    }

    /**
     * Send event to inform about unknown card action on card reader
     */
    public void informAboutCardUnknown() {
        LOG.debug(cardReader.getName() + "-Device: \"card unknown\"");
        EventBus.getDefault().post(new CardUnknownEvent(cardReader));
    }

    /**
     * Send event to inform about timeout card on card reader
     */
    public void informAboutCardTimeout() {
        LOG.debug(cardReader.getName() + "-Device: \"card timeout\"");
        EventBus.getDefault().post(new CardTimeoutEvent(cardReader));
    }
}
