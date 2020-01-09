/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.command;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class CommandApduTest {
    @Test
    public void testCase1Apdu() {
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04);

        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04 }, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(0x00, cApdu.getData().length);
        Assert.assertEquals(0x00, cApdu.getNc());
        Assert.assertNull(cApdu.getNe());
    }

    @Test
    public void testCase2sApdu() {
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, 127);

        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x7F }, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(0x00, cApdu.getData().length);
        Assert.assertEquals(0x00, cApdu.getNc());
        Assert.assertEquals(127, (int) cApdu.getNe());
    }

    @Test
    public void testCase2sExpect256BytesApdu() {
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, 256);

        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00 }, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(0x00, cApdu.getData().length);
        Assert.assertEquals(0x00, cApdu.getNc());
        Assert.assertEquals(256, (int) cApdu.getNe());
    }

    @Test
    public void testCase2sExpectBytesWildcardApdu() {
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, AbstractApdu.EXPECTED_LENGTH_WILDCARD_SHORT);

        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00 }, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(0x00, cApdu.getData().length);
        Assert.assertEquals(0x00, cApdu.getNc());
        Assert.assertEquals(AbstractApdu.EXPECTED_LENGTH_WILDCARD_SHORT, (int) cApdu.getNe());
    }

    @Test
    public void testCase2eApdu() {
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, 257);

        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00, 0x01, 0x01 }, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(0x00, cApdu.getData().length);
        Assert.assertEquals(0x00, cApdu.getNc());
        Assert.assertEquals(257, (int) cApdu.getNe());
    }

    @Test
    public void testCase2eExpect65535BytesApdu() {
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, 65535);

        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00, (byte) 0xff, (byte) 0xff }, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(0x00, cApdu.getData().length);
        Assert.assertEquals(0x00, cApdu.getNc());
        Assert.assertEquals(65535, (int) cApdu.getNe());
    }

    @Test
    public void testCase2eExpect65536BytesApdu() {
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, 65536);

        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00, 0x00, 0x00 }, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(0x00, cApdu.getData().length);
        Assert.assertEquals(0x00, cApdu.getNc());
        Assert.assertEquals(65536, (int) cApdu.getNe());
    }

    @Test
    public void testCase2eExpectBytesWildcardApdu() {
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, AbstractApdu.EXPECTED_LENGTH_WILDCARD_EXTENDED);

        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00, 0x00, 0x00 }, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(0x00, cApdu.getData().length);
        Assert.assertEquals(0x00, cApdu.getNc());
        Assert.assertEquals(AbstractApdu.EXPECTED_LENGTH_WILDCARD_EXTENDED, (int) cApdu.getNe());
    }

    @Test
    public void testCase3sApdu() {
        byte[] cmdData = new byte[] { 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a };
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData);

        byte[] header_plus_lc = new byte[] { 0x01, 0x02, 0x03, 0x04, (byte) cmdData.length };
        byte[] apdu = Arrays.copyOf(header_plus_lc, header_plus_lc.length + cmdData.length);
        System.arraycopy(cmdData, 0, apdu, header_plus_lc.length, cmdData.length);

        Assert.assertArrayEquals(apdu, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(cmdData.length, cApdu.getData().length);
        Assert.assertEquals(cmdData.length, cApdu.getNc());
        Assert.assertArrayEquals(cmdData, cApdu.getData());
        Assert.assertNull(cApdu.getNe());
    }

    @Test
    public void testCase3s255BytesDataApdu() {
        byte[] cmdData = new byte[255];
        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(cmdData);
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData);

        byte[] header_plus_lc = new byte[] { 0x01, 0x02, 0x03, 0x04, (byte) 0xFF };
        byte[] apdu = Arrays.copyOf(header_plus_lc, header_plus_lc.length + cmdData.length);
        System.arraycopy(cmdData, 0, apdu, header_plus_lc.length, cmdData.length);

        Assert.assertArrayEquals(apdu, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(0xff, cApdu.getData().length);
        Assert.assertEquals(0xff, cApdu.getNc());
        Assert.assertArrayEquals(cmdData, cApdu.getData());
        Assert.assertNull(cApdu.getNe());
    }

    @Test
    public void testCase3e256BytesDataApdu() {
        byte[] cmdData = new byte[256];
        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(cmdData);
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData);

        byte[] header_plus_lc = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00, 0x01, 0x00 };
        byte[] apdu = Arrays.copyOf(header_plus_lc, header_plus_lc.length + cmdData.length);
        System.arraycopy(cmdData, 0, apdu, header_plus_lc.length, cmdData.length);

        Assert.assertArrayEquals(apdu, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(256, cApdu.getData().length);
        Assert.assertEquals(256, cApdu.getNc());
        Assert.assertArrayEquals(cmdData, cApdu.getData());
        Assert.assertNull(cApdu.getNe());
    }

    @Test
    public void testCase3e65535BytesDataApdu() {
        int dataSize = 65535;
        byte[] cmdData = new byte[dataSize];
        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(cmdData);
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData);

        byte[] header_plus_lc = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00, (byte) (dataSize >> 8), (byte) dataSize };
        byte[] apdu = Arrays.copyOf(header_plus_lc, header_plus_lc.length + cmdData.length);
        System.arraycopy(cmdData, 0, apdu, header_plus_lc.length, cmdData.length);

        Assert.assertArrayEquals(apdu, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(dataSize, cApdu.getData().length);
        Assert.assertEquals(dataSize, cApdu.getNc());
        Assert.assertArrayEquals(cmdData, cApdu.getData());
        Assert.assertNull(cApdu.getNe());
    }

    @Test
    public void testCase4sApdu() {
        byte[] cmdData = new byte[] { 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a };
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData, 127);

        byte[] header_plus_lc = new byte[] { 0x01, 0x02, 0x03, 0x04, (byte) cmdData.length };
        byte[] apdu = Arrays.copyOf(header_plus_lc, header_plus_lc.length + cmdData.length + 1);
        System.arraycopy(cmdData, 0, apdu, header_plus_lc.length, cmdData.length);
        apdu[header_plus_lc.length + cmdData.length] = 0x7F;

        Assert.assertArrayEquals(apdu, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(cmdData.length, cApdu.getData().length);
        Assert.assertEquals(cmdData.length, cApdu.getNc());
        Assert.assertArrayEquals(cmdData, cApdu.getData());
        Assert.assertEquals(127, (int) cApdu.getNe());
    }

    @Test
    public void testCase4s255BytesDataApdu() {
        byte[] cmdData = new byte[255];
        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(cmdData);
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData, 256);

        byte[] header_plus_lc = new byte[] { 0x01, 0x02, 0x03, 0x04, (byte) 0xFF };
        byte[] apdu = Arrays.copyOf(header_plus_lc, header_plus_lc.length + cmdData.length + 1);
        System.arraycopy(cmdData, 0, apdu, header_plus_lc.length, cmdData.length);
        apdu[header_plus_lc.length + cmdData.length] = (byte) 0x00;

        Assert.assertArrayEquals(apdu, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(0xff, cApdu.getData().length);
        Assert.assertEquals(0xff, cApdu.getNc());
        Assert.assertArrayEquals(cmdData, cApdu.getData());
        Assert.assertEquals(256, (int) cApdu.getNe());
    }

    @Test
    public void testCase4e256BytesDataApdu() {
        byte[] cmdData = new byte[256];
        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(cmdData);
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData, 127);

        byte[] header_plus_lc = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00, 0x01, 0x00 };
        byte[] apdu = Arrays.copyOf(header_plus_lc, header_plus_lc.length + cmdData.length + 2);
        System.arraycopy(cmdData, 0, apdu, header_plus_lc.length, cmdData.length);
        apdu[header_plus_lc.length + cmdData.length] = (byte) 0x00;
        apdu[header_plus_lc.length + cmdData.length + 1] = (byte) 0x7F;

        Assert.assertArrayEquals(apdu, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(256, cApdu.getData().length);
        Assert.assertEquals(256, cApdu.getNc());
        Assert.assertArrayEquals(cmdData, cApdu.getData());
        Assert.assertEquals(127, (int) cApdu.getNe());
    }

    @Test
    public void testCase4e255BytesDataExpectedLength257Apdu() {
        byte[] cmdData = new byte[255];
        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(cmdData);
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData, 257);

        byte[] header_plus_lc = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00, 0x00, (byte) 0xFF };
        byte[] apdu = Arrays.copyOf(header_plus_lc, header_plus_lc.length + cmdData.length + 2);
        System.arraycopy(cmdData, 0, apdu, header_plus_lc.length, cmdData.length);
        apdu[header_plus_lc.length + cmdData.length] = (byte) 0x01;
        apdu[header_plus_lc.length + cmdData.length + 1] = (byte) 0x01;

        Assert.assertArrayEquals(apdu, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(255, cApdu.getData().length);
        Assert.assertEquals(255, cApdu.getNc());
        Assert.assertArrayEquals(cmdData, cApdu.getData());
        Assert.assertEquals(257, (int) cApdu.getNe());
    }

    @Test
    public void testCase4e65535BytesDataExpectedLength65535Apdu() {
        byte[] cmdData = new byte[65535];
        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(cmdData);
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData, 65535);

        byte[] header_plus_lc = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00, (byte) 0xFF, (byte) 0xFF };
        byte[] apdu = Arrays.copyOf(header_plus_lc, header_plus_lc.length + cmdData.length + 2);
        System.arraycopy(cmdData, 0, apdu, header_plus_lc.length, cmdData.length);
        apdu[header_plus_lc.length + cmdData.length] = (byte) 0xFF;
        apdu[header_plus_lc.length + cmdData.length + 1] = (byte) 0xFF;

        Assert.assertArrayEquals(apdu, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(65535, cApdu.getData().length);
        Assert.assertEquals(65535, cApdu.getNc());
        Assert.assertArrayEquals(cmdData, cApdu.getData());
        Assert.assertEquals(65535, (int) cApdu.getNe());
    }

    @Test
    public void testCase4e65535BytesDataExpectedLength65536Apdu() {
        byte[] cmdData = new byte[65535];
        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(cmdData);
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData, 65536);

        byte[] header_plus_lc = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00, (byte) 0xFF, (byte) 0xFF };
        byte[] apdu = Arrays.copyOf(header_plus_lc, header_plus_lc.length + cmdData.length + 2);
        System.arraycopy(cmdData, 0, apdu, header_plus_lc.length, cmdData.length);
        apdu[header_plus_lc.length + cmdData.length] = (byte) 0x00;
        apdu[header_plus_lc.length + cmdData.length + 1] = (byte) 0x00;

        Assert.assertArrayEquals(apdu, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(65535, cApdu.getData().length);
        Assert.assertEquals(65535, cApdu.getNc());
        Assert.assertArrayEquals(cmdData, cApdu.getData());
        Assert.assertEquals(65536, (int) cApdu.getNe());
    }

    @Test
    public void testCase4e65535BytesDataExpectedLengthWildcardShortApdu() {
        byte[] cmdData = new byte[65535];
        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(cmdData);
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData, AbstractApdu.EXPECTED_LENGTH_WILDCARD_SHORT);

        byte[] header_plus_lc = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00, (byte) 0xFF, (byte) 0xFF };
        byte[] apdu = Arrays.copyOf(header_plus_lc, header_plus_lc.length + cmdData.length + 2);
        System.arraycopy(cmdData, 0, apdu, header_plus_lc.length, cmdData.length);
        apdu[header_plus_lc.length + cmdData.length] = (byte) 0x01;
        apdu[header_plus_lc.length + cmdData.length + 1] = (byte) 0x00;

        Assert.assertArrayEquals(apdu, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(65535, cApdu.getData().length);
        Assert.assertEquals(65535, cApdu.getNc());
        Assert.assertArrayEquals(cmdData, cApdu.getData());
        Assert.assertEquals(AbstractApdu.EXPECTED_LENGTH_WILDCARD_SHORT, (int) cApdu.getNe());
    }

    @Test
    public void testCase4e65535BytesDataExpectedLengthWildcardExtendedApdu() {
        byte[] cmdData = new byte[65535];
        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(cmdData);
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData, AbstractApdu.EXPECTED_LENGTH_WILDCARD_EXTENDED);

        byte[] header_plus_lc = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x00, (byte) 0xFF, (byte) 0xFF };
        byte[] apdu = Arrays.copyOf(header_plus_lc, header_plus_lc.length + cmdData.length + 2);
        System.arraycopy(cmdData, 0, apdu, header_plus_lc.length, cmdData.length);
        apdu[header_plus_lc.length + cmdData.length] = (byte) 0x00;
        apdu[header_plus_lc.length + cmdData.length + 1] = (byte) 0x00;

        Assert.assertArrayEquals(apdu, cApdu.getBytes());
        Assert.assertEquals(0x01, cApdu.getCla());
        Assert.assertEquals(0x02, cApdu.getIns());
        Assert.assertEquals(0x03, cApdu.getP1());
        Assert.assertEquals(0x04, cApdu.getP2());
        Assert.assertEquals(65535, cApdu.getData().length);
        Assert.assertEquals(65535, cApdu.getNc());
        Assert.assertArrayEquals(cmdData, cApdu.getData());
        Assert.assertEquals(AbstractApdu.EXPECTED_LENGTH_WILDCARD_EXTENDED, (int) cApdu.getNe());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCmdDataTooBig() {
        int dataSize = 65536;
        byte[] cmdData = new byte[dataSize];
        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(cmdData);
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData);
    }

    @Test
    public void testEquality() {
        byte[] cmdData1 = new byte[65535];
        byte[] cmdData2;

        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(cmdData1);
        cmdData2 = Arrays.copyOf(cmdData1, cmdData1.length);

        CommandApdu cApdu1 = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData1, 65536);
        CommandApdu cApdu2 = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData2, 65536);

        Assert.assertEquals(cApdu2, cApdu1);
    }

    @Test
    public void testCloning() {
        int dataSize = 65535;
        byte[] cmdData = new byte[dataSize];
        Random rnd = new Random(System.currentTimeMillis());
        rnd.nextBytes(cmdData);
        CommandApdu cApdu = new CommandApdu(0x01, 0x02, 0x03, 0x04, cmdData);

        byte[] apdu1 = cApdu.getBytes();
        byte[] apdu2 = cApdu.getBytes();

        byte[] data1 = cApdu.getData();
        byte[] data2 = cApdu.getData();

        Assert.assertArrayEquals(data2, data1);
        Assert.assertArrayEquals(apdu2, apdu1);

        Assert.assertNotEquals(data2, data1);
        Assert.assertNotEquals(apdu2, apdu1);
    }

}
