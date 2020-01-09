/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.spi;

import de.gematik.ti.cardreader.provider.api.ICardReaderController;
import de.gematik.ti.cardreader.provider.api.entities.IProviderDescriptor;

/**
 * include::{userguide}/CRPAPI_Overview.adoc[tag=ServiceProvider]
 *
 */
public interface ICardReaderControllerProvider {
    /**
     * Return the Controller for specific ICardReaderController implementation
     * 
     * @return - return CardReaderController for a specific card reader interface
     */
    ICardReaderController getCardReaderController();

    /**
     * Descriptor class for information about the card reader provider and functionality
     * @return - Descriptor object for this card reader provider
     */
    IProviderDescriptor getDescriptor();
}
