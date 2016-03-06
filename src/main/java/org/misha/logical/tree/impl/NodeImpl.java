package org.misha.logical.tree.impl;

import org.misha.logical.Node;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA. User: user Date: 05.11.11 Time: 11:43
 *
 * @param <T> type of content
 */
public class NodeImpl<T> implements Node<T> {
    private final ArrayList<Node<T>> children;
    private Node<T> parent;
    private T content;
    private int depth;
    private String name;

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
    final String name() {
        return name;
    }

    @Override
    public Object getName() {
        return name;
    }

    @Override
    public void setName(String n) {
        name = n;
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
