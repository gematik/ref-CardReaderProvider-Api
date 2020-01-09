/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.entities;

import java.io.Serializable;

/**
 * include::{userguide}/CRPAPI_Overview.adoc[tag=ProviderDescriptor]
 *
 */
public interface IProviderDescriptor extends Serializable {
    /**
     * Unique card reader provider name
     * @return - Unique name
     */
    String getName();

    /**
     * The licence of card reader provider
     * @return - licence text
     */
    String getLicense();

    /**
     * Detailed Description about the card reader provider
     * @return - detailed description text
     */
    String getDescription();

    /**
     * Very short description about the card reader provider. 
     * @return - short description text
     */
    String getShortDescription();

    /**
     * Name of implementing class
     * @return - class name
     */
    String getClassName();
}
