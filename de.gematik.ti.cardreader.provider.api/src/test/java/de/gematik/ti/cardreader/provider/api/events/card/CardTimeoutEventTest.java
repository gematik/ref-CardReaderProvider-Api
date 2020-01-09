/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.events.card;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import de.gematik.ti.cardreader.provider.api.ICardReader;

public class CardTimeoutEventTest {

    private final ICardReader cardReader = Mockito.mock(ICardReader.class);

    @Test
    public void testCreation() {
        CardTimeoutEvent event = new CardTimeoutEvent(cardReader);
        Assert.assertEquals(cardReader, event.getCardReader());
    }
}
