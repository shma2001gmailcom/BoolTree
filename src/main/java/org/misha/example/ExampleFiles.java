package org.misha.example;

import org.apache.log4j.Logger;
import org.misha.logical.Node;
import org.misha.logical.evaluator.BoolNodeProcessor;
import org.misha.util.PropertiesReader;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import static org.misha.util.ContentReader.readContent;

/**
 * @author misha
 */
final class ExampleFiles {
    private static final Logger log = Logger.getLogger(ExampleFiles.class);
    private static final byte PATH = 0;
    private static final byte CONTENT = 1;
    private final String pathRule, contentRule;
    private final LinkedList<String> results = new LinkedList<String>();

    private ExampleFiles(final String p, final String c) {
        pathRule = p;
        contentRule = c;
    }

    public static void main(String[] args) throws Exception {
        final ExampleFiles example =
                new ExampleFiles(PropertiesReader.getProperty("path"), PropertiesReader.getProperty("content"));
        for (Iterator<Map.Entry<String, String>> it = PropertiesReader.iterator(); it.hasNext(); ) {
            Map.Entry<String, String> entry = it.next();
            log.debug(entry.getKey() + " is " + entry.getValue());
        }
        example.search(new File(PropertiesReader.getProperty("base.folder")));
        while (!example.getResults().isEmpty()) {
            log.info(example.results.pop());
        }
    }

    private BoolNodeProcessor evaluator(byte var, final File file) {
        BoolNodeProcessor result;
        switch (var) {
            case PATH:
                result = new BoolNodeProcessor() {

                    @Override
                    public boolean evaluateLeaf(final Node<String> leaf) {
                        return file.getAbsolutePath().contains(leaf.getContent());
                    }
                };
                break;
            case CONTENT:
                result = new BoolNodeProcessor() {

                    @Override
                    public boolean evaluateLeaf(final Node<String> leaf) {
                        String content = readContent(file);
                        return content.contains(leaf.getContent());
                    }
                };
                break;
            default:
                throw new IllegalArgumentException("evaluator() admits only two values as its first argument."
                );
        }
        return result;
    }

    private void search(final File file) {
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

    private LinkedList<String> getResults() {
        return results;
    }
}
