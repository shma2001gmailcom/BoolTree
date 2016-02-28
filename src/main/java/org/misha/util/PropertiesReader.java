package org.misha.util;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.*;
import static org.apache.commons.io.FileUtils.lineIterator;
import static org.apache.commons.io.LineIterator.closeQuietly;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * author: misha
 * date: 2/27/16 12:08 PM.
 */
public final class PropertiesReader {
    private static final Logger log = Logger.getLogger(PropertiesReader.class);
    private static final String path = "./resources/rule.properties";
    private final File properties;
    private final Map<String, String> map;

    private PropertiesReader(final File file) {
        properties = file;
        map = fillMap();
    }

    private Map<String, String> fillMap() {
        Map<String, String> result = null;
        LineIterator it = null;
        try {
            it = lineIterator(properties, "UTF-8");
            result = iterate(it);
        } catch (IOException e) {
            log.fatal("file not found\n" + e);
        } finally {
            closeQuietly(it);
        }
        return result;
    }

    private ImmutableMap<String, String> iterate(final LineIterator it) {
        Builder<String, String> builder = new Builder<String, String>();
        while (it.hasNext()) {
            final String line = it.nextLine();
            if (isNotBlank(line)) {
                final String[] parts = line.split("=");
                if (parts.length == 2) {
                    builder = builder.put(parts[0].trim(), parts[1].trim());
                }
            }
        }
        return builder.build();
    }

    public static String getProperty(final String key) {
        return Holder.instance.map.get(key);
    }

    public static Iterator<Map.Entry<String, String>> iterator() {
        Map<String, String> clone = new HashMap<String, String>();
        for(Map.Entry<String, String> entry : Holder.instance.map.entrySet()) {
            clone.put(entry.getKey(), entry.getValue());
        }
        return clone.entrySet().iterator();
    }

    private static class Holder {
        private static final PropertiesReader instance = new PropertiesReader(new File(path));

        static {
            instance.fillMap();
        }
    }
}
