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
package org.spongepowered.api.plugin;

import org.slf4j.Logger;
import org.spongepowered.api.locale.Dictionary;
import org.spongepowered.api.locale.NullDictionary;
import org.spongepowered.api.service.ServiceManager;

import java.util.Collection;
import java.util.Optional;

/**
 * The manager that manages plugins. This manager can retrieve
 * {@link PluginContainer}s from {@link Plugin} instances, getting
 * {@link Logger}s, etc.
 */
public interface PluginManager {

    /**
     * Get the plugin container from an instance.
     *
     * @param instance The instance
     * @return The container
     */
    Optional<PluginContainer> fromInstance(Object instance);

    /**
     * Finds the {@link PluginContainer} for the specified plugin instance.
     *
     * @param instance Plugin instance
     * @return Plugin container of instance
     * @throws PluginNotFoundException if the instance could not be found
     */
    default PluginContainer find(Object instance) {
        return fromInstance(instance).orElseThrow(() -> new PluginNotFoundException(instance));
    }

    /**
     * Retrieves a {@link PluginContainer} based on its ID.
     *
     * @param id The plugin ID
     * @return The plugin, if available
     */
    Optional<PluginContainer> getPlugin(String id);

    /**
     * Gets the {@link Logger} for the {@link PluginContainer}.
     *
     * @param plugin The plugin
     * @return The logger
     */
    Logger getLogger(PluginContainer plugin);

    /**
     * Gets a {@link Collection} of all {@link PluginContainer}s.
     *
     * @return The plugins
     */
    Collection<PluginContainer> getPlugins();

    /**
     * Checks if a plugin is loaded based on its ID.
     * This may contain plugins/mods from other systems in some implementations.
     *
     * @param id the id of the {@link Plugin}
     * @return {@code true} if loaded {@code false} if not loaded.
     */
    boolean isLoaded(String id);

    /**
     * Gets the id of the {@link Plugin}.
     *
     * @return The id
     */
    default String getId(Object plugin) {
        return find(plugin).getId();
    }

    /**
     * Gets the name of the {@link Plugin}.
     *
     * @return The name
     */
    default String getName(Object plugin) {
        return find(plugin).getName();
    }

    /**
     * Gets the version of the {@link Plugin}.
     *
     * @return The name
     */
    default String getVersion(Object plugin) {
        return find(plugin).getVersion();
    }

    /**
     * Returns the internal {@link ServiceManager} for the specified plugin
     * instance.
     *
     * @param plugin Instance of plugin
     * @return Internal plugin service manager
     */
    default ServiceManager getServiceManager(Object plugin) {
        return find(plugin).getServiceManager();
    }

    /**
     * Returns the {@link Dictionary} for the given plugin instance.
     *
     * @param plugin Plugin instance
     * @return Dictionary provided by plugin or {@link NullDictionary} if none
     *         was provided
     */
    default Dictionary getDictionary(Object plugin) {
        return getServiceManager(plugin).provideFirst(Dictionary.class).orElse(new NullDictionary(plugin));
    }

}
