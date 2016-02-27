package org.misha.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * author: misha
 * date: 2/27/16 12:08 PM.
 */
public final class PropertiesReader {
    private static final Logger log = Logger.getLogger(PropertiesReader.class);
    private static final String path = "./resources/rule.properties";
    private final File properties;
    private final Map<String, String> map = new HashMap<String, String>();

    private PropertiesReader(final File properties) {
        this.properties = properties;
    }

    private void fillMap() {
        LineIterator it = null;
        try {
            it = FileUtils.lineIterator(properties, "UTF-8");
            while (it.hasNext()) {
                final String line = it.nextLine();
                if (StringUtils.isNotBlank(line)) {
                    final String[] parts = line.split("=");
                    if (parts.length == 2) {
                        map.put(parts[0].trim(), parts[1].trim());
                    }
                }
            }
        } catch (IOException e) {
            log.fatal("file not found\n" + e);
        } finally {
            LineIterator.closeQuietly(it);
        }
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

    public static class Holder {
        private static final PropertiesReader instance = new PropertiesReader(new File(path));

        static {
            instance.fillMap();
        }
    }
}
