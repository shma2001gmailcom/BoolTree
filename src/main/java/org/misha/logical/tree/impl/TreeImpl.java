package org.misha.logical.tree.impl;

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
    private final Node<T> root;

    public TreeImpl(final Node<T> node) {
        root = node;
    }

    @Override
    public Node<T> root() {
        return root;
    }

    @Override
    public final String toString() {
        StringBuffer sb = new StringBuffer();
        Node<T> node = root;
        if (!node.isLeaf()) {
            sb = sb.append(node.toString()).append("->[");
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
}
