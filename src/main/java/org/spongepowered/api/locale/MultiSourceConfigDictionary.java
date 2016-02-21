package org.spongepowered.api.locale;

import ninja.leaping.configurate.ConfigurationNode;

import java.io.IOException;
import java.util.Locale;

/**
 * Represents a {@link ConfigDictionary} with a different source per-locale.
 */
public class MultiSourceConfigDictionary extends AbstractConfigDictionary {

    public MultiSourceConfigDictionary(Object subject, Locale defaultLocale) {
        super(subject, defaultLocale);
    }

    @Override
    public ConfigurationNode load(Locale locale) throws IOException {
        ConfigurationNode localeNode = super.load(locale);
        this.bundles.put(locale, new ConfigResourceBundle(localeNode));
        return localeNode;
    }

}
