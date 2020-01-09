/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api;

import org.junit.Assert;
import org.junit.Test;

import de.gematik.ti.cardreader.provider.api.card.CardException;
import de.gematik.ti.cardreader.provider.api.card.ICard;

public class ICardReaderTest {

    private final ICardReader cardReader = new ICardReader() {

        @Override
        public void initialize() {

        }

        @Override
        public boolean isInitialized() {
            return false;
        }

        @Override
        public ICard connect() throws CardException {
            return null;
        }

        @Override
        public String getName() {
            return "getNameResult";
        }

        @Override
        public boolean isCardPresent() throws CardException {
            return false;
        }

    };
    private final ICardReader cardReader2 = new ICardReader() {

        @Override
        public String getDisplayName() {
            return "getDisplayName";
        }

        @Override
        public boolean isCardPresent() throws CardException {
            return false;
        }

        @Override
        public void initialize() {

        }

        @Override
        public boolean isInitialized() {
            return false;
        }

        @Override
        public ICard connect() throws CardException {
            return null;
        }

        @Override
        public String getName() {
            return "getNameResult";
        }

    };

    @Test
    public void getDisplayName() {
        Assert.assertEquals(cardReader.getName(), cardReader.getDisplayName());
        Assert.assertNotEquals(cardReader2.getName(), cardReader2.getDisplayName());
    }
}
