package org.spongepowered.api.locale;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

/**
 * Default {@link Dictionary} implementation used by Sponge. This
 * implementation first tries to resolve the dictionary within a directory
 * and falls-back to the class loader if that file is not found.
 */
public class DefaultDictionary extends SimpleConfigDictionary {

    protected final Path path;

    public DefaultDictionary(Object subject, Locale defaultLocale, Path path) {
        super(subject, defaultLocale);
        this.path = path;
        this.resolver.primary(this::resolveSource);
    }

    protected InputStream resolveSource() throws IOException {
        if (Files.exists(this.path)) {
            return Files.newInputStream(this.path);
        }
        return this.subject.getClass().getClassLoader().getResourceAsStream(this.path.getFileName().toString());
    }
}
