/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api;

import org.junit.Assert;
import org.junit.Test;

import de.gematik.ti.cardreader.provider.api.card.CardException;
import de.gematik.ti.cardreader.provider.api.listener.InitializationStatus;

/**
 * Unit-Tests für die Klasse {@link AbstractCardReaderController}
 *
 */
public class AbstractCardReaderControllerTest extends AbstractCardEventTransmitterTest/* NOCS(hoa): JUnit test class for testing abstract class */ {

    @Test
    public void testConnectionListeners() throws CardException {

        abstractCardReaderController.informAboutReaderConnection(reader, InitializationStatus.INIT_SUCCESS);
        Assert.assertEquals(1, listener.getConnectedReaders());
        Assert.assertEquals(1, listener1.getConnectedReaders());
        Assert.assertEquals(1, listener.getInitializedReaders());
        Assert.assertEquals(1, listener1.getInitializedReaders());

        abstractCardReaderController.informAboutReaderConnection(reader, InitializationStatus.INIT_SUCCESS);
        Assert.assertEquals(2, listener.getConnectedReaders());
        Assert.assertEquals(2, listener1.getConnectedReaders());
        Assert.assertEquals(2, listener.getInitializedReaders());
        Assert.assertEquals(2, listener1.getInitializedReaders());

        abstractCardReaderController.informAboutReaderDisconnection(reader);
        Assert.assertEquals(1, listener.getConnectedReaders());
        Assert.assertEquals(1, listener1.getConnectedReaders());

        abstractCardReaderController.informAboutReaderDisconnection(reader);
        Assert.assertEquals(0, listener.getConnectedReaders());
        Assert.assertEquals(0, listener1.getConnectedReaders());

    }

}
