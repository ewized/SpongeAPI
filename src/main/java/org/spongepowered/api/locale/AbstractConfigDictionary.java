package org.spongepowered.api.locale;

import ninja.leaping.configurate.ConfigurationNode;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * Abstract implementation of {@link ConfigDictionary}.
 */
public class AbstractConfigDictionary extends AbstractRemoteDictionary implements ConfigDictionary {

    protected final Map<Locale, ConfigResourceBundle> bundles = new HashMap<>();

    public AbstractConfigDictionary(Object subject, Locale defaultLocale) {
        super(subject, defaultLocale);
    }

    @Override
    public Optional<ConfigurationNode> getNode(Locale locale) {
        Optional<ConfigResourceBundle> bundle = getBundle(locale);
        if (bundle.isPresent()) {
            return Optional.of(bundle.get().getNode());
        }
        return Optional.empty();
    }

    @Override
    public Optional<ConfigResourceBundle> getBundle(Locale locale) {
        return Optional.ofNullable(this.bundles.get(locale));
    }

    @Override
    public void setBundle(Locale locale, ConfigResourceBundle bundle) {
        this.bundles.put(locale, bundle);
    }
}
