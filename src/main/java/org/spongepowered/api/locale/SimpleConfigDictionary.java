/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.locale;

import static com.google.common.base.Preconditions.checkNotNull;

import ninja.leaping.configurate.ConfigurationNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a simple implementation of {@link ConfigDictionary}.
 */
public class SimpleConfigDictionary extends AbstractRemoteDictionary implements ConfigDictionary {

    protected final Map<Locale, ConfigResourceBundle> bundles = new HashMap<>();

    public SimpleConfigDictionary(Object subject, Locale defaultLocale) {
        super(subject, defaultLocale);
    }

    @Override
    public ConfigurationNode load(Locale locale) throws IOException {
        ConfigurationNode localeNode = ConfigDictionary.super.load(locale).getNode(locale.toString());
        this.bundles.put(locale, new ConfigResourceBundle(localeNode));
        return localeNode;
    }

    @Override
    public Optional<ConfigurationNode> getNode(Locale locale) {
        ConfigResourceBundle bundle = this.bundles.get(locale);
        if (bundle != null) {
            return Optional.of(bundle.getNode());
        }
        return Optional.empty();
    }

    @Override
    public Optional<ConfigResourceBundle> getBundle(Locale locale) {
        return Optional.ofNullable(this.bundles.get(locale));
    }

    @Override
    public void setBundle(Locale locale, ConfigResourceBundle bundle) {
        this.bundles.put(checkNotNull(locale, "locale"), checkNotNull(bundle, "bundle"));
    }
}
