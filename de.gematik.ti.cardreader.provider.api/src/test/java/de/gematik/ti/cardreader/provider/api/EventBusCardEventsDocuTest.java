/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.junit.Assert;
import org.junit.Test;

import de.gematik.ti.cardreader.provider.api.events.card.CardPresentEvent;

public class EventBusCardEventsDocuTest {

    @Test
    public void connectTest() {
        Assert.assertFalse(EventBus.getDefault().hasSubscriberForEvent(CardPresentEvent.class));
        registerOnEventBus();
        Assert.assertTrue(EventBus.getDefault().hasSubscriberForEvent(CardPresentEvent.class));
        unregisterOnEventBus();
        Assert.assertFalse(EventBus.getDefault().hasSubscriberForEvent(CardPresentEvent.class));
    }

    // tag::CardPresentEvent[]
    @Subscribe
    public void cardPresent(final CardPresentEvent cardPresentEvent) {
        // Do Something
    }

    public void registerOnEventBus() {
        EventBus.getDefault().register(this);
    }

    public void unregisterOnEventBus() {
        EventBus.getDefault().unregister(this);
    }
    // end::CardPresentEvent[]
}
