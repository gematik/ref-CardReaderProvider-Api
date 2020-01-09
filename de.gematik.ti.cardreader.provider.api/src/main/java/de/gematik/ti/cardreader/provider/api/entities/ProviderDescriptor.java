/*
 * ${GEMATIK_LICENSE}
 */

package de.gematik.ti.cardreader.provider.api.entities;

/**
 * include::{userguide}/CRPAPI_Overview.adoc[tag=ProviderDescriptor]
 *
 */
public class ProviderDescriptor implements IProviderDescriptor {

    private static final long serialVersionUID = -1309738583934832731L;
    private final String name;
    private String license;
    private String description;
    private String shortDescription;
    private String className;

    public ProviderDescriptor(final String name) {
        this.name = name;
        this.license = "";
        this.description = "";
        this.shortDescription = "";
        this.className = "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLicense() {
        return license;
    }

    public void setLicense(final String license) {
        this.license = license;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String getClassName() {
        return className;
    }

    public void setClassName(final String className) {
        this.className = className;
    }

    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(final String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
