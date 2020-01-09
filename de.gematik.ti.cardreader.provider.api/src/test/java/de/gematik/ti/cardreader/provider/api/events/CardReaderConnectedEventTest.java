/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.events;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import de.gematik.ti.cardreader.provider.api.ICardReader;
import de.gematik.ti.cardreader.provider.api.listener.InitializationStatus;

public class CardReaderConnectedEventTest {

    private final ICardReader cardReader = Mockito.mock(ICardReader.class);

    @Test
    public void testCreation() {
        CardReaderConnectedEvent event = new CardReaderConnectedEvent(cardReader, InitializationStatus.INIT_SUCCESS);
        CardReaderConnectedEvent event2 = new CardReaderConnectedEvent(cardReader, InitializationStatus.INIT_FAILED);
        Assert.assertEquals(cardReader, event.getCardReader());
        Assert.assertEquals(cardReader, event2.getCardReader());
        Assert.assertEquals(InitializationStatus.INIT_SUCCESS, event.getInitStatus());
        Assert.assertEquals(InitializationStatus.INIT_FAILED, event2.getInitStatus());
    }
}
