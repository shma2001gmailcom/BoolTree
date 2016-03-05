package org.misha.logical;

import java.util.Collection;

/**
 * Author: misha shevelin
 * Date: 1/23/12
 * Time: 10:34 PM
 *
 * @param <T> a type of content
 */
public interface Node<T> {

    Node<T> getParent();

    void setParent(Node<T> n);

    Collection<Node<T>> getChildren();

    void setDepth(int depth);

    T getContent();

    void setContent(T f);

    void addChild(Node<T> child);

    boolean isLeaf();

    boolean isRoot();

    boolean removeChild(Node<T> n);

    Object getName();

    void setName(String s);

    /**
     * @return new node with same content and same children (up to leafs)
     */
    Node<T> copy();
}
