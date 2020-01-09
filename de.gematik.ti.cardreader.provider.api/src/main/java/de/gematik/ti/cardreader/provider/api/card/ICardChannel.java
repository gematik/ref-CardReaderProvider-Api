/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.card;

import de.gematik.ti.cardreader.provider.api.command.ICommandApdu;
import de.gematik.ti.cardreader.provider.api.command.IResponseApdu;

/**
 * Interface to a (logical) channel of a smart card.
 * A channel object is used to send commands to and to receive answers from a smartcard.
 * This is done by sneding so called A-PDUs {@linkplain ICommandApdu}to smartcard. A smartcard returns
 * a {@linkplain IResponseApdu}
 * An ICardChannel object can be obtained by calling the method
 *  {@linkplain ICard#openBasicChannel} or {@linkplain ICard#openLogicalChannel()}.
 *
 */
public interface ICardChannel {

    /**
     * Returns the Card this channel is associated with.
     *
     * @return the Card this channel is associated with
     */
    ICard getCard();

    /**
     * Returns the channel number of this CardChannel.
     * A channel number of 0 indicates the basic channel.
     *
     * @return the channel number of this ICardChannel.
     * @throws IllegalStateException
     *         if this channel has been {@linkplain #close closed} or
     *         if the corresponding Card has been {@linkplain ICard#disconnect disconnected}.
     */
    int getChannelNumber();

    /**
     * Identify whether a channel supports APDU extended length commands and
     * appropriate responses
     */
    boolean isExtendedLengthSupported();

    /**
     * Max length of APDU body in bytes.
     */
    int getMaxMessageLength();

    /**
     * Max length of an APDU response.
     */
    int getMaxResponseLength();

    /**
     * Transmits the specified {@linkplain ICommandApdu} to the associated smartcard and returns the
     * {@linkplain IResponseApdu}.
     *
     * The CLA byte of the {@linkplain ICommandApdu} is automatically adjusted to match the channel number of this card channel
     * since the channel number is coded into CLA byte of a command APDU according to ISO 7816-4.
     *
     *
     * Note that this method should be used to transmit `MANAGE CHANNEL` APDUs in order to open or
     * close logical channels. Logical channels should be managed using the
     * {@linkplain ICard#openLogicalChannel()} and {@linkplain ICardChannel#close CardChannel.close()} methods.
     *
     * Implementations should transparently handle artifacts of the transmission protocol.
     *
     *
     * The ResponseAPDU returned by this method is the result after this processing has been performed.
     *
     *
     * @param command
     *            the {@linkplain ICommandApdu command APDU} to be send to the smartcard
     * @return the {@linkplain IResponseApdu response APDU} received from the card
     * @throws IllegalStateException
     *             if this channel has been {@linkplain #close closed} or if the corresponding Card has been {@linkplain Card#disconnect disconnected}.
     * @throws NullPointerException
     *             if command is null
     * @throws CardException
     *             if the card operation failed
     */
    IResponseApdu transmit(ICommandApdu command) throws CardException;

    /**
     * Closes this ICardChannel. The logical channel is closed by issuing a `MANAGE CHANNEL` command that should use the format
     * `[xx 70 80 0n]` where `n` is the channel number of this channel and `xx` is the `CLA` byte that encodes this
     * logical channel and has all other bits set to 0. After this method returns, calling other methods in this class will raise an IllegalStateException.
     * 
     * Note: the basic channel cannot be closed using this method. It can be closed by calling {@link ICard#disconnect}.
     *
     * @throws CardException
     *             if the card operation failed
     * @throws IllegalStateException
     *             if this CardChannel represents a connection the basic logical channel
     */
    void close() throws CardException;
}
