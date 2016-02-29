package org.misha.logical.tree.impl;

import org.apache.log4j.Logger;
import org.misha.logical.Node;
import org.misha.logical.Tree;

/**
 * author: mshevelin
 * date: 10/24/11
 * Time: 2:38 PM
 *
 * @param <T> a type of nodes content
 */

public class TreeImpl<T> implements Tree<T> {
    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(TreeImpl.class);
    private final Node<T> root;

    @SuppressWarnings("javadoc")
    public TreeImpl(final Node<T> node) {
        root = node;
    }

    @Override
    public Node<T> root() {
        return root;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public final org.misha.logical.Iterator<Node> iterator() {
        return new Iterator();
    }

    @Override
    public final String toString() {
        StringBuffer sb = new StringBuffer();
        Node<T> node = root;
        if (!node.isLeaf()) {
            sb = sb.append(node.getName()).append("->[");
            Node<T> child;
            for (java.util.Iterator<Node<T>> it = node.getChildren().iterator(); it.hasNext(); ) {
                child = it.next();
                Tree<T> tree = new TreeImpl<T>(child);
                sb.append(tree.toString());
                if (it.hasNext()) {
                    sb = sb.append(", ");
                }
            }
            sb = sb.append("]");
        } else {
            sb = sb.append(node.getName());
        }
        return sb.toString();
    }

    @SuppressWarnings("rawtypes")
    private class Iterator implements org.misha.logical.Iterator<Node> {
        private final Stack stack = new Stack();

        private Iterator() {
            stack.push(root);
        }

        @Override
        public final boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public final Node next() {
            return stack.pop();
        }
    }
}
