package org.misha.logical.tree.impl;

import org.misha.logical.Node;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Author: mshevelin
 * Date: 10/24/11
 * Time: 10:03 AM
 */
public abstract class Searcher<T> implements Iterable<Node<T>> {
    private final Node<T> root;
    private final Set<Node<T>> results = new HashSet<Node<T>>();

    public Searcher(final Node<T> tree) {
        root = tree;
    }

    public final Node<T> search() {
        Node<T> node;
        Queue<T> queue = new Queue<T>();
        queue.add(root);
        while (!queue.isEmpty()) {
            node = queue.pop();
            if (isSuitable(node)) {
                results.add(node);
            }
        }
        return null;
    }

    public Iterator<Node<T>> iterator() {
        return results.iterator();
    }

    public int size() {
        return results.size();
    }

    public abstract boolean isSuitable(final Node<T> node);
}
