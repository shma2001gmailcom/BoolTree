package org.misha.example;

import org.apache.log4j.Logger;
import org.misha.logical.evaluator.StrictEvaluator;
import org.misha.logical.tree.Node;
import org.misha.util.PropertiesReader;

import java.io.*;
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

    /**
     * public constructor
     */
    public Example() {
        log.info("=================Path Criteria=====================");
        log.info(PropertiesReader.getProperty("path"));
        log.info("=================Content Criteria==================");
        log.info(PropertiesReader.getProperty("content"));
        log.info("===================================================");
        pathRule = PropertiesReader.getProperty("path");
        contentRule = PropertiesReader.getProperty("content");
    }

    @SuppressWarnings("javadoc")
    public static void main(String[] args) {
        Example example = new Example();
        for(Iterator<Map.Entry<String, String>> it = PropertiesReader.iterator(); it.hasNext();) {
            Map.Entry<String, String> entry = it.next();
            log.debug(entry.getKey() + " is " + entry.getValue());
        }
        example.search(new File(PropertiesReader.getProperty("base.folder")));
        while (!example.getResults().isEmpty()) {
            log.info(example.results.pop());
        }
    }

    private StrictEvaluator evaluator(byte var, final File file) {
        StrictEvaluator result;
        switch (var) {
            case PATH:
                result = new StrictEvaluator() {

                    @Override
                    public boolean[] evaluateLeaf(Node<String> leaf) {
                        return new boolean[]{file.getAbsolutePath().contains(leaf.getContent())};
                    }
                };
                break;
            case CONTENT:
                result = new StrictEvaluator() {

                    @Override
                    public boolean[] evaluateLeaf(Node<String> leaf) {
                        InputStream is = null;
                        StringBuilder sb = null;
                        try {
                            is = new FileInputStream(file.getAbsolutePath());
                            sb = new StringBuilder();
                            int b;
                            while ((b = is.read()) != -1) {
                                sb = sb.append((char) b);
                            }
                        } catch (FileNotFoundException e) {
                            log.error(e);
                        } catch (IOException e) {
                            log.error(e);
                        } finally {
                            if (is != null) {
                                try {
                                    is.close();
                                } catch (IOException e) {
                                    log.error(e);
                                }
                            }
                        }
                        if (sb != null) {
                            return new boolean[]{sb.toString().contains(leaf.getContent())};
                        }
                        throw new IllegalStateException("sb must be not null");
                    }
                };
                break;
            default:
                throw new IllegalArgumentException(
                        "org.misha.logical.evaluator() admits only two values as its first argument."
                );
        }
        return result;
    }

    @SuppressWarnings("javadoc")
    public void search(final File file) {
        if (file.isFile()) {
            if (evaluator(PATH, file).getValue(pathRule)) {
                if (evaluator(CONTENT, file).getValue(contentRule)) {
                    results.add(file.getAbsolutePath());
                }
            }
        } else {
            File[] listFiles;
            listFiles = file.listFiles();
            if (listFiles != null) {
                for (File f : listFiles) {
                    search(f);
                }
            }
        }
    }

    @SuppressWarnings("javadoc")
    public LinkedList<String> getResults() {
        return results;
    }
}
