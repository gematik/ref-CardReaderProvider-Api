/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.command;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class ResponseApduTest {
    @Test
    public void testEmptyDataResponse9000() {
        ResponseApdu rApdu = new ResponseApdu(new byte[] { (byte) 0x90, 0x00 });
        Assert.assertEquals(0x90, rApdu.getSW1());
        Assert.assertEquals(0x00, rApdu.getSW2());
        Assert.assertEquals(0x9000, rApdu.getSW());
        Assert.assertEquals(0, rApdu.getNr());
        Assert.assertEquals(0, rApdu.getData().length);
        Assert.assertArrayEquals(new byte[] { (byte) 0x90, 0x00 }, rApdu.getBytes());
        Assert.assertEquals(new ResponseApdu(new byte[] { (byte) 0x90, 0x00 }), rApdu);
    }

    @Test
    public void testEmptyDataResponse6383() {
        ResponseApdu rApdu = new ResponseApdu(new byte[] { 0x63, (byte) 0x83 });
        Assert.assertEquals(0x63, rApdu.getSW1());
        Assert.assertEquals(0x83, rApdu.getSW2());
        Assert.assertEquals(0x6383, rApdu.getSW());
        Assert.assertEquals(0, rApdu.getNr());
        Assert.assertEquals(0, rApdu.getData().length);
        Assert.assertArrayEquals(new byte[] { 0x63, (byte) 0x83 }, rApdu.getBytes());
        Assert.assertEquals(new ResponseApdu(new byte[] { (byte) 0x63, (byte) 0x83 }), rApdu);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShortApdu() {
        ResponseApdu rApdu = new ResponseApdu(new byte[] { 0x63 });
    }

    @Test
    public void tesResponseWithData() {
        ResponseApdu rApdu1 = new ResponseApdu(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05, (byte) 0x90, 0x09 });
        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05 }, rApdu1.getData());
        Assert.assertEquals(0x90, rApdu1.getSW1());
        Assert.assertEquals(0x09, rApdu1.getSW2());

        ResponseApdu rApdu2 = new ResponseApdu(new byte[] { 0x00, (byte) 0x90, 0x09 });
        Assert.assertArrayEquals(new byte[] { 0x00 }, rApdu2.getData());
        Assert.assertEquals(0x90, rApdu2.getSW1());
        Assert.assertEquals(0x09, rApdu2.getSW2());

        byte[] data = new byte[65536];
        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(data);
        byte[] apdu = Arrays.copyOf(data, data.length + 2);
        apdu[data.length] = (byte) 0x90;

        ResponseApdu rApdu3 = new ResponseApdu(apdu);
        Assert.assertArrayEquals(data, rApdu3.getData());
        Assert.assertEquals(0x90, rApdu3.getSW1());
        Assert.assertEquals(0x00, rApdu3.getSW2());
    }

    @Test
    public void testCloning() {
        ResponseApdu rApdu = new ResponseApdu(new byte[] { (byte) 0x90, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05 });
        byte[] apduCopy1 = rApdu.getBytes();
        byte[] apduCopy2 = rApdu.getBytes();
        Assert.assertNotEquals(apduCopy2, apduCopy1);
        byte[] data1 = rApdu.getData();
        byte[] data2 = rApdu.getData();
        Assert.assertNotEquals(data2, data1);
    }

    @Test
    public void testEquality() {
        ResponseApdu rApdu1 = new ResponseApdu(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05, (byte) 0x90, 0x00 });
        ResponseApdu rApdu2 = new ResponseApdu(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05, (byte) 0x90, 0x00 });
        ResponseApdu rApdu3 = new ResponseApdu(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05, (byte) 0x90, 0x01 });
        ResponseApdu rApdu4 = new ResponseApdu(new byte[] { 0x00, 0x02, 0x03, 0x04, 0x05, (byte) 0x90, 0x00 });
        Assert.assertEquals(rApdu2, rApdu1);
        Assert.assertNotEquals(rApdu3, rApdu1);
        Assert.assertNotEquals(rApdu4, rApdu1);
    }
}
