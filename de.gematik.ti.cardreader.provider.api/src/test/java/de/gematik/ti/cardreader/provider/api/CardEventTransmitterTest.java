/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CardEventTransmitterTest extends AbstractCardEventTransmitterTest {

    private final ICardReader reader = Mockito.mock(ICardReader.class);

    @Test
    public void testCardPresentAbsentListeners() {
        CardEventTransmitter cardEventTransmitter = abstractCardReaderController.createCardEventTransmitter(reader);

        cardEventTransmitter.informAboutCardPresent();
        Assert.assertEquals(1, listener.getConnectedCards());
        Assert.assertEquals(1, listener1.getConnectedCards());

        cardEventTransmitter.informAboutCardPresent();
        Assert.assertEquals(2, listener.getConnectedCards());
        Assert.assertEquals(2, listener1.getConnectedCards());

        cardEventTransmitter.informAboutCardAbsent();
        Assert.assertEquals(1, listener.getConnectedCards());
        Assert.assertEquals(1, listener1.getConnectedCards());

        cardEventTransmitter.informAboutCardAbsent();
        Assert.assertEquals(0, listener.getConnectedCards());
        Assert.assertEquals(0, listener1.getConnectedCards());

    }

    @Test
    public void testCardTimeoutListeners() {
        CardEventTransmitter cardEventTransmitter = abstractCardReaderController.createCardEventTransmitter(reader);

        Assert.assertEquals(0, listener.getTimeoutCards());
        Assert.assertEquals(0, listener1.getTimeoutCards());
        cardEventTransmitter.informAboutCardTimeout();
        Assert.assertEquals(1, listener.getTimeoutCards());
        Assert.assertEquals(1, listener1.getTimeoutCards());
        cardEventTransmitter.informAboutCardTimeout();
        Assert.assertEquals(2, listener.getTimeoutCards());
        Assert.assertEquals(2, listener1.getTimeoutCards());

    }

    @Test
    public void testCardUnknownListeners() {
        CardEventTransmitter cardEventTransmitter = abstractCardReaderController.createCardEventTransmitter(reader);

        Assert.assertEquals(0, listener.getUnknownCards());
        Assert.assertEquals(0, listener1.getUnknownCards());
        cardEventTransmitter.informAboutCardUnknown();
        Assert.assertEquals(1, listener.getUnknownCards());
        Assert.assertEquals(1, listener1.getUnknownCards());
        cardEventTransmitter.informAboutCardUnknown();
        Assert.assertEquals(2, listener.getUnknownCards());
        Assert.assertEquals(2, listener1.getUnknownCards());
    }

}
