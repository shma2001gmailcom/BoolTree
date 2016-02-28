package org.misha.logical.tree.impl;

import org.apache.log4j.Logger;
import org.misha.logical.tree.Node;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA. User: user Date: 05.11.11 Time: 11:43
 *
 * @param <T> type of content
 */
public class NodeImpl<T> implements Node<T> {
    private static final Logger log = Logger.getLogger(NodeImpl.class);
    private final ArrayList<Node<T>> children;
    private Node<T> parent;
    private T content;
    private int depth;
    private String name;

    /**
     * @param c a content of the node
     */
    public NodeImpl(T c) {
        depth = 0;
        children = new ArrayList<Node<T>>();
        parent = null;
        content = c;
        name = "Node(" + c.toString() + ")";
    }

    @Override
    public final Node<T> getParent() {
        return parent;
    }

    @Override
    public void setParent(Node<T> n) {
        parent = n;
    }

    @Override
    public final ArrayList<Node<T>> getChildren() {
        return children;
    }

    @Override
    public final int getDepth() {
        int d = 0;
        Node<T> node = this;
        while (node.getParent() != null) {
            ++d;
            node = node.getParent();
        }
        return d;
    }

    @Override
    public void setDepth(int d) {
        depth = d;
    }

    @Override
    public final T getContent() {
        return content;
    }

    @Override
    public final void setContent(T f) {
        content = f;
    }

    @Override
    public void addChild(Node<T> child) {
        children.add(child);
        child.setParent(this);
        child.setDepth(depth + 1);
    }

    @Override
    public final boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public final boolean isRoot() {
        return parent == null;
    }

    /**
     * @return name
     */
    public final String name() {
        return name;
    }

    @Override
    public Object getName() {
        return name;
    }

    @Override
    public void setName(String n) {// to override name
        name = n;
    }

    @Override
    public boolean removeChild(Node<T> n) {
        if (children.contains(n)) {
            if (n.isLeaf()) {
                return children.remove(n);
            } else {
                for (Node<T> n1 : n.getChildren()) {
                    n.removeChild(n1);
                }
                return children.remove(n);
            }
        }
        return false;
    }

    @Override
    public final String toString() {
        return name();
    }

    @Override
    public NodeImpl<T> copy() {
        NodeImpl<T> result = new NodeImpl<T>(this.content);
        if (isLeaf()) {
            return result;
        }
        NodeImpl<T> orig = this;
        for (Node<T> child : orig.getChildren()) {
            result.addChild(child.copy());
        }
        return result;
    }
}
