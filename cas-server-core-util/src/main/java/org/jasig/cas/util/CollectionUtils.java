package org.jasig.cas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This is {@link CollectionUtils}.
 *
 * @author Misagh Moayyed
 * @since 4.3.0
 */
public final class CollectionUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionUtils.class);

    private CollectionUtils() {}

    /**
     * Convert the object given into a {@link Collection} instead.
     * @param obj the object to convert into a collection
     * @return The collection instance containing the object provided
     */
    @SuppressWarnings("unchecked")
    public static Set<Object> convertValueToCollection(final Object obj) {
        final Set<Object> c = new HashSet<>();
        if (obj == null) {
            LOGGER.debug("Converting null obj to empty collection");
        } else if (obj instanceof Collection) {
            c.addAll((Collection<Object>) obj);
            LOGGER.debug("Converting multi-valued attribute [{}] for the authentication result", obj);
        } else if (obj instanceof Map) {
            throw new UnsupportedOperationException(Map.class.getCanonicalName() + " is not supported");
        } else if (obj.getClass().isArray()) {
            c.addAll(Arrays.asList((Object[]) obj));
            LOGGER.debug("Converting array attribute [{}] for the authentication result", obj);
        } else {
            c.add(obj);
            LOGGER.debug("Converting attribute [{}] for the authentication result", obj);
        }
        return c;
    }
}
