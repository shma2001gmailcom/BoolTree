package org.misha.util;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * author: misha
 * date: 3/6/16
 * time: 9:44 AM
 */
public final class ContentReader {
    private static final Logger log = Logger.getLogger(ContentReader.class);

    private ContentReader() {
    }

    public static String readContent(final File file) {
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try {
            is = new FileInputStream(file.getAbsolutePath());
            int b;
            while ((b = is.read()) != -1) {
                sb = sb.append((char) b);
            }
        } catch (Exception e) {
            log.error(e);
        } finally {
            IOUtils.closeQuietly(is);
        }
        return sb.toString();
    }
}
