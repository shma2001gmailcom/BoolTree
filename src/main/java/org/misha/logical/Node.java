package org.misha.logical;

import java.util.Collection;

/**
 * Author: misha shevelin Date: 1/23/12 Time: 10:34 PM
 *
 * @param <T> a type of content
 */
public interface Node<T> {

    @SuppressWarnings("javadoc")
    Node<T> getParent();

    @SuppressWarnings("javadoc")
    void setParent(Node<T> n);

    @SuppressWarnings("javadoc")
    Collection<Node<T>> getChildren();

    @SuppressWarnings("javadoc")
    void setDepth(int depth);

    @SuppressWarnings("javadoc")
    T getContent();

    @SuppressWarnings("javadoc")
    void setContent(T f);

    @SuppressWarnings("javadoc")
    void addChild(Node<T> child);

    @SuppressWarnings("javadoc")
    boolean isLeaf();

    @SuppressWarnings("javadoc")
    boolean isRoot();

    @SuppressWarnings("javadoc")
    boolean removeChild(Node<T> n);

    @SuppressWarnings("javadoc")
    Object getName();

    @SuppressWarnings("javadoc")
    void setName(String s);

    /**
     * @return new node with same content and same children (up to leafs)
     */
    Node<T> copy();
}
