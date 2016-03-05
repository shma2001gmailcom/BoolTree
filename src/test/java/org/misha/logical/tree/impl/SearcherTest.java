package org.misha.logical.tree.impl;

import org.junit.Test;
import org.misha.logical.Node;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class SearcherTest {

    @Test
    public void testSearch() throws Exception {
        Node<String> root = new NodeImpl<String>("1");
        Node<String> node10 = new NodeImpl<String>("10");
        Node<String> node11 = new NodeImpl<String>("11");
        Node<String> node100 = new NodeImpl<String>("100");
        Node<String> node101 = new NodeImpl<String>("101");
        Node<String> node1000 = new NodeImpl<String>("1000");
        Node<String> node1001 = new NodeImpl<String>("1001");
        root.addChild(node10);
        root.addChild(node11);
        node10.addChild(node100);
        node10.addChild(node101);
        node100.addChild(node1000);
        node100.addChild(node1001);
        search(root, ".*(01).*");
    }

    private void search(final Node<String> root, final String toSearch) {
        final Pattern pattern = Pattern.compile(toSearch);
        final Searcher<String> searcher = new Searcher<String>(root) {

            @Override
            public boolean isSuitable(final Node<String> node) {
                Matcher matcher = pattern.matcher(node.getContent());
                return matcher.find();
            }
        };
        searcher.search();
        assertTrue(searcher.size() == 2);
        for (Node<String> node : searcher) {
            assertTrue(node.getContent().contains("01"));
        }
    }
}