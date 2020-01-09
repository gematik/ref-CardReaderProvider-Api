/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.card;

import org.junit.Assert;
import org.junit.Test;

public class CardProtocolTest {
    @Test
    public void testFromInt() {
        Assert.assertEquals(CardProtocol.T0, CardProtocol.fromInt(0));
        Assert.assertEquals(CardProtocol.T1, CardProtocol.fromInt(1));
        Assert.assertEquals(CardProtocol.T15, CardProtocol.fromInt(15));
        Assert.assertEquals(CardProtocol.T1, CardProtocol.fromInt(0xF1));
        Assert.assertEquals(CardProtocol.T1, CardProtocol.fromInt(0xFFF1));
        Assert.assertNull(CardProtocol.fromInt(3));
    }
}
