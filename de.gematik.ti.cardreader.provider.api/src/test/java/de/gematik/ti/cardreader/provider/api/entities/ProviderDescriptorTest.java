/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProviderDescriptorTest {

    public static final String DUMMY_NAME = "DummyName";
    public static final String CLASSNAME = "CLASSNAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String SHORT_DESC = "SHORT_DESC";
    public static final String LICENSE = "LICENSE";
    private IProviderDescriptor providerDescriptor;

    @Before
    public void setUp() throws Exception {
        providerDescriptor = new ProviderDescriptor(DUMMY_NAME);
    }

    @Test
    public void testName() {
        Assert.assertNotNull(providerDescriptor.getName());
        Assert.assertNotNull(providerDescriptor.getClassName());
        Assert.assertNotNull(providerDescriptor.getDescription());
        Assert.assertNotNull(providerDescriptor.getShortDescription());
        Assert.assertNotNull(providerDescriptor.getLicense());
        Assert.assertEquals(providerDescriptor.getName(), DUMMY_NAME);
    }

    @Test
    public void testFields() {
        ProviderDescriptor pd = (ProviderDescriptor) providerDescriptor;
        pd.setClassName(CLASSNAME);
        pd.setDescription(DESCRIPTION);
        pd.setShortDescription(SHORT_DESC);
        pd.setLicense(LICENSE);

        Assert.assertEquals(providerDescriptor.getLicense(), LICENSE);
        Assert.assertEquals(providerDescriptor.getShortDescription(), SHORT_DESC);
        Assert.assertEquals(providerDescriptor.getDescription(), DESCRIPTION);
        Assert.assertEquals(providerDescriptor.getClassName(), CLASSNAME);
        Assert.assertEquals(providerDescriptor.getName(), DUMMY_NAME);

    }
}
