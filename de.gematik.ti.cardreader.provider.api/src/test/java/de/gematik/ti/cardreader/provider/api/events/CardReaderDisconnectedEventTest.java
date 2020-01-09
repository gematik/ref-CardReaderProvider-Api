/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.events;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import de.gematik.ti.cardreader.provider.api.ICardReader;

public class CardReaderDisconnectedEventTest {

    private final ICardReader cardReader = Mockito.mock(ICardReader.class);

    @Test
    public void testCreation() {
        CardReaderDisconnectedEvent event = new CardReaderDisconnectedEvent(cardReader);
        Assert.assertEquals(cardReader, event.getCardReader());
    }
}
