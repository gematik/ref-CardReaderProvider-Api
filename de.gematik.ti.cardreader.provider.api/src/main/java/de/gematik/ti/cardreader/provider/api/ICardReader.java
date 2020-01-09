/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api;

import de.gematik.ti.cardreader.provider.api.card.CardException;
import de.gematik.ti.cardreader.provider.api.card.ICard;

/**
 * include::{userguide}/CRPAPI_Overview.adoc[tag=CardReader]
 *
 */
public interface ICardReader {

    /**
     * start initialisation
     */
    void initialize();

    /**
     * Return the current initialisation status
     * 
     * @return
     *          true: if card reader is initialized
     *          false: card reader not operational
     */
    boolean isInitialized();

    /**
     * Establishes a connection to the card. If a connection has previously established using the specified protocol, this method returns the same Card object
     * as the previous call.
    
     * @return Card object
     *
     * @throws
     *        CardException - if a connection could not be established using the specified protocol or if a connection has previously been established using a
     *        different protocol
     */
    ICard connect() throws CardException;

    /**
     * Returns the unique name of this reader.
     *
     * @return the unique name of this reader.
     */
    String getName();

    /**
     * Returns the system name of this reader.
     *
     * @return the system name of this reader.
     */
    default String getDisplayName() {
        return getName();
    }

    /**
     * Returns whether a card is present in this reader.
     *
     * @return whether a card is present in this reader.
     *
     * @throws CardException - if the status could not be determined
     */
    boolean isCardPresent() throws CardException;
}
