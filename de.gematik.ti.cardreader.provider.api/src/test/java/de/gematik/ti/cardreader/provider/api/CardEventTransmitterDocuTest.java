/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api;

import org.junit.Test;
import org.mockito.Mockito;

public class CardEventTransmitterDocuTest extends AbstractCardEventTransmitterTest {

    // tag::CardEventTransmitter[]
    private final ICardReader reader = Mockito.mock(ICardReader.class);

    @Test
    public void sendCardEvent() {
        /* abstractCardReaderController; */ // The Provider specific Controller implementation
        CardEventTransmitter cardEventTransmitter = abstractCardReaderController.createCardEventTransmitter(reader);
        cardEventTransmitter.informAboutCardPresent();
    }

    // end::CardEventTransmitter[]
}
