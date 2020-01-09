/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.junit.Assert;
import org.junit.Test;

import de.gematik.ti.cardreader.provider.api.events.CardReaderConnectedEvent;

public class EventBusConnectionsDocuTest {

    @Test
    public void connectTest() {
        Assert.assertFalse(EventBus.getDefault().hasSubscriberForEvent(CardReaderConnectedEvent.class));
        registerOnEventBus();
        Assert.assertTrue(EventBus.getDefault().hasSubscriberForEvent(CardReaderConnectedEvent.class));
        unregisterOnEventBus();
        Assert.assertFalse(EventBus.getDefault().hasSubscriberForEvent(CardReaderConnectedEvent.class));
    }

    // tag::CardReaderConnectedEvent[]
    @Subscribe
    public void cardReaderConnected(final CardReaderConnectedEvent connectedEvent) {
        // Do Something
    }

    public void registerOnEventBus() {
        EventBus.getDefault().register(this);
    }

    public void unregisterOnEventBus() {
        EventBus.getDefault().unregister(this);
    }
    // end::CardReaderConnectedEvent[]
}
