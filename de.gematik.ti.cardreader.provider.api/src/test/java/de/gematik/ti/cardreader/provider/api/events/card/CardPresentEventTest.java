/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.events.card;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import de.gematik.ti.cardreader.provider.api.ICardReader;

public class CardPresentEventTest {

    private final ICardReader cardReader = Mockito.mock(ICardReader.class);

    @Test
    public void testCreation() {
        CardPresentEvent event = new CardPresentEvent(cardReader);
        Assert.assertEquals(cardReader, event.getCardReader());
    }
}
