package org.misha.example;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.misha.logical.Node;
import org.misha.logical.evaluator.BoolNodeProcessor;
import org.misha.util.PropertiesReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author misha
 */
public class Example {
    private static final Logger log = Logger.getLogger(Example.class);
    private static final byte PATH = 0;
    private static final byte CONTENT = 1;
    private final String pathRule, contentRule;
    private final LinkedList<String> results = new LinkedList<String>();

    public Example() {
        log.info("=================Path Criteria=====================");
        log.info(PropertiesReader.getProperty("path"));
        log.info("=================Content Criteria==================");
        log.info(PropertiesReader.getProperty("content"));
        log.info("===================================================");
        pathRule = PropertiesReader.getProperty("path");
        contentRule = PropertiesReader.getProperty("content");
    }

    public static void main(String[] args) throws Exception {
        Example example = new Example();
        for (Iterator<Map.Entry<String, String>> it = PropertiesReader.iterator(); it.hasNext(); ) {
            Map.Entry<String, String> entry = it.next();
            log.debug(entry.getKey() + " is " + entry.getValue());
        }
        example.search(new File(PropertiesReader.getProperty("base.folder")));
        while (!example.getResults().isEmpty()) {
            log.info(example.results.pop());
        }
    }

    private static boolean containsGoal(final Node<String> leaf, final File file) {
        final StringBuilder sb = readContent(file);
        if (sb != null) {
            return sb.toString().contains(leaf.getContent());
        }
        throw new IllegalStateException("sb must be not null");
    }

    private static StringBuilder readContent(final File file) {
        InputStream is = null;
        StringBuilder sb = null;
        try {
            is = new FileInputStream(file.getAbsolutePath());
            sb = new StringBuilder();
            int b;
            while ((b = is.read()) != -1) {
                sb = sb.append((char) b);
            }
        } catch (Exception e) {
            log.error(e);
        } finally {
            IOUtils.closeQuietly(is);
        }
        return sb;
    }

    private BoolNodeProcessor evaluator(byte mode, final File file) {
        BoolNodeProcessor result;
        switch (mode) {
            case PATH:
                result = new BoolNodeProcessor() {

                    @Override
                    public boolean evaluateLeaf(Node<String> leaf) {
                        return file.getAbsolutePath().contains(leaf.getContent());
                    }
                };
                break;
            case CONTENT:
                result = new BoolNodeProcessor() {

                    @Override
                    public boolean evaluateLeaf(Node<String> leaf) {
                        return containsGoal(leaf, file);
                    }
                };
                break;
            default:
                throw new IllegalArgumentException("evaluator() admits only two values as its first argument.");
        }
        return result;
    }

    public void search(final File file) throws Exception {
        if (file.isFile()) {
            if (evaluator(PATH, file).evaluate(pathRule)) {
                if (evaluator(CONTENT, file).evaluate(contentRule)) {
                    results.add(file.getAbsolutePath());
                }
            }
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File f : listFiles) {
                    search(f);
                }
            }
        }
    }

    public LinkedList<String> getResults() {
        return results;
    }
}
