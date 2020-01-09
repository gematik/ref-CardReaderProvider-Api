/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api;

import java.util.Collection;

import de.gematik.ti.cardreader.provider.api.card.CardException;

/**
 * include::{userguide}/CRPAPI_Overview.adoc[tag=CardReaderController]
 * 
 */
public interface ICardReaderController {

    /**
     * Collect all card reader found by specific provider
     * @return Collection of card reader
     * @throws CardException
     */
    Collection<ICardReader> getCardReaders() throws CardException;

}
