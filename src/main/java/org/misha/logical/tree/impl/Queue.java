package org.misha.logical.tree.impl;

import org.apache.log4j.Logger;
import org.misha.logical.tree.Node;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA. User: mshevelin Date: 10/21/11 Time: 6:50 PM
 *
 * @param <T> a type of queue nodes content
 */
public final class Queue<T> {
    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(Queue.class);
    private final LinkedList<Node<T>> list = new LinkedList<Node<T>>();

    /**
     * @return and remove first node
     */
    public Node<T> pop() {
        Node<T> node = list.pop();
        if (!node.isLeaf()) {
            list.addAll(node.getChildren());
        }
        return node;
    }

    /**
     * @param n a node to add
     */
    public void add(final Node<T> n) {
        list.add(n);
    }

    @SuppressWarnings("unused")
    private void addAll(final Collection<Node<T>> c) {
        list.addAll(c);
    }

    /**
     * @return boolean
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
