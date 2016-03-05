package org.misha.logical.tree.impl;

import org.misha.logical.Node;

/**
 * Author: mshevelin Date: 10/24/11 Time: 10:03 AM
 */
public class Searcher {
    private final Node<String> root;

    public Searcher(final Node<String> tree) {
        root = tree;
    }

    public final Node<String> search(final Object o) {
        Node<String> node;
        Queue<String> queue = new Queue<String>();
        queue.add(root);
        while (!queue.isEmpty()) {
            node = queue.pop();
            if (node.getContent().equals(o)) {
                return node;
            }
        }
        return null;
    }
}
