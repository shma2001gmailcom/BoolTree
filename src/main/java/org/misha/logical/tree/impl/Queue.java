package org.misha.logical.tree.impl;

import org.misha.logical.Node;

import java.util.LinkedList;

/**
 * author: mshevelin
 * date: 10/21/11
 * time: 6:50 PM
 *
 * @param <T> a type of queue nodes content
 */
public final class Queue<T> {
    private final LinkedList<Node<T>> list = new LinkedList<Node<T>>();

    public Node<T> pop() {
        final Node<T> node = list.pop();
        if (!node.isLeaf()) {
            list.addAll(node.getChildren());
        }
        return node;
    }

    public void add(final Node<T> n) {
        list.add(n);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
