package de.gematik.ti.cardreader.provider.api;

import java.util.Collection;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gematik.ti.cardreader.provider.api.card.CardException;
import de.gematik.ti.cardreader.provider.api.events.CardReaderConnectedEvent;
import de.gematik.ti.cardreader.provider.api.events.CardReaderDisconnectedEvent;
import de.gematik.ti.cardreader.provider.api.events.card.CardAbsentEvent;
import de.gematik.ti.cardreader.provider.api.events.card.CardPresentEvent;
import de.gematik.ti.cardreader.provider.api.events.card.CardTimeoutEvent;
import de.gematik.ti.cardreader.provider.api.events.card.CardUnknownEvent;

public abstract class AbstractCardEventTransmitterTest {
    protected static final int RETRY_COUNTER = 100;
    protected static final int WAIT_TIME_BEFORE_RETRY = 100;
    private static final Logger LOGGER = LoggerFactory.getLogger(CardEventTransmitterTest.class);

    protected final AbstractCardReaderController abstractCardReaderController = new AbstractCardReaderController() {
        @Override
        public Collection<ICardReader> getCardReaders() throws CardException {
            return null;
        }
    };
    protected final ConnectionListener listener = new ConnectionListener();
    protected final ConnectionListener listener1 = new ConnectionListener();
    protected final ICardReader reader = Mockito.mock(ICardReader.class);

    @Before
    public void init() throws InterruptedException {
        LOGGER.debug("init AbstractCardReaderControllerTest start");
        EventBus.getDefault().register(listener);
        EventBus.getDefault().register(listener1);
        int counter = 0;
        while ((!EventBus.getDefault().hasSubscriberForEvent(CardReaderConnectedEvent.class)
                || !EventBus.getDefault().hasSubscriberForEvent(CardReaderDisconnectedEvent.class)) && counter < RETRY_COUNTER) {
            Thread.sleep(WAIT_TIME_BEFORE_RETRY);
            counter++;
        }
        if (counter >= RETRY_COUNTER) {
            throw new InterruptedException("Initialize JUnit Test failed");
        }
        LOGGER.debug("init AbstractCardReaderControllerTest done");
    }

    @After
    public void close() {
        EventBus.getDefault().unregister(listener);
        EventBus.getDefault().unregister(listener1);
    }

    public class ConnectionListener {
        private final Logger logger = LoggerFactory.getLogger(AbstractCardReaderControllerTest.ConnectionListener.class);
        private int connectedReaders = 0;
        private int connectedCards = 0;
        private int unknownCards = 0;
        private int timeoutCards = 0;
        private int initializedReaders = 0;

        @Subscribe
        public void cardReaderConnected(final CardReaderConnectedEvent connectedEvent) {
            connectedReaders += 1;
            logger.debug("cardReaderConnected, Count: " + connectedReaders);
            switch (connectedEvent.getInitStatus()) {
                case INIT_SUCCESS:
                    ++initializedReaders;
                    break;
                default:
                    break;
            }
        }

        @Subscribe
        public void cardReaderDisconnected(final CardReaderDisconnectedEvent disconnectedEvent) {
            connectedReaders -= 1;
            logger.debug("cardReaderDisconnected, Count: " + connectedReaders);

        }

        // tag::CardPresentEventDocu[]

        public void registerOnEventBus() {
            EventBus.getDefault().register(this);
        }

        @Subscribe
        public void cardConnected(final CardPresentEvent connectedEvent) {
            connectedCards += 1;
            logger.debug("cardConnected, Count: " + connectedCards);
        }

        @Subscribe
        public void cardDisconnected(final CardAbsentEvent disconnectedEvent) {
            connectedCards -= 1;
            logger.debug("cardDisconnected, Count: " + connectedCards);

        }
        // end::CardPresentEventDocu[]

        @Subscribe
        public void cardTimeout(final CardTimeoutEvent cardTimeoutEvent) {
            timeoutCards += 1;
            logger.debug("cardTimeout, Count: " + timeoutCards);

        }

        @Subscribe
        public void cardUnknown(final CardUnknownEvent cardUnknownEvent) {
            unknownCards += 1;
            logger.debug("cardUnknown, Count: " + unknownCards);

        }

        public int getConnectedReaders() {
            return connectedReaders;
        }

        public int getConnectedCards() {
            return connectedCards;
        }

        public int getInitializedReaders() {
            return initializedReaders;
        }

        public int getUnknownCards() {
            return unknownCards;
        }

        public int getTimeoutCards() {
            return timeoutCards;
        }
    }
}
