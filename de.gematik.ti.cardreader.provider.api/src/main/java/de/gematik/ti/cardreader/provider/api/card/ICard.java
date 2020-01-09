/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.card;

/**
 * Interface to a smartcard object which is obtained from a card reader object
 * of a certain card reader provider
 *
 */
public interface ICard {

    /**
     * Returns the Atr of this card
     *
     * @return the Atr of this card.
     */
    Atr getATR();

    /**
     * Returns the transport protocol used.
     *
     * @return the transport protocol used, for example T0 for "T=0" or T1 for "T=1"
     */
    CardProtocol getProtocol();

    /**
     * Open the basic communication channel to the card.
     * - Note: the basic channel assumes the channel number 0.
     *
     * @return The (connected) basic card channel
     * @throws SecurityException
     *              if a SecurityManager exists and the caller does not have the required permission
     * @throws IllegalStateException
     *              if this card object has been disposed of via the {@linkplain #disconnect disconnect()} method
     * @throws CardException
     *              if there is an communication error with underlying layers
     */
    ICardChannel openBasicChannel() throws CardException;

    /**
     *  Open a new logical channel. The channel is opened issuing a <code>MANAGE CHANNEL</code> command that
     *  should use the format [0x0, 0x70, 0x0, 0x0, 0x1].
     *  
     * @return The (connected) logical card channel
     * @throws SecurityException
     *             if a SecurityManager exists and the caller does not have the required permission
     * @throws IllegalStateException
     *             if this card object has been disposed of via the {@linkplain #disconnect disconnect()} method
     * @throws CardException
     *              is a new logical channel could not be opened
    
     */
    ICardChannel openLogicalChannel() throws CardException;

    /**
     * Disconnects the connection with this card. After this method returns, calling methods on this object
     * or in CardChannels associated with this object that require interaction with the card will
     * raise an IllegalStateException.
     *
     * @param reset
     *            whether to reset the card after disconnecting.
     * @throws SecurityException
     *             if a SecurityManager exists and the caller does not have the required {@linkplain CardPermission permission}
     * @throws CardException
     *             if the card operation failed
     */
    void disconnect(boolean reset) throws CardException;
}
