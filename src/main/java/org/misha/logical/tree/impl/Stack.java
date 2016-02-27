package org.misha.logical.tree.impl;

import org.misha.logical.tree.Node;

import java.util.LinkedList;

/**
 * author: mshevelin
 * date: 10/24/11
 * Time: 2:38 PM
 */
public final class Stack {
    private final LinkedList<Node<? extends Object>> list = new LinkedList<Node<? extends Object>>();

    /**
     * @return and remove first node
     */
    public Node<?> pop() {
        Node<?> node = list.pop();
        if (!node.isLeaf()) {
            for (Node<?> child : node.getChildren()) {
                push(child);
            }
        }
        return node;
    }

    /**
     * @param n a node to add
     */
    public void push(final Node<?> n) {
        list.push(n);
    }

    /**
     * @return boolean
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
