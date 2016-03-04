package org.misha.logical;

/**
 * Author: misha shevelin Date: 1/23/12 Time: 10:57 PM
 *
 * @param <T> the type of nodes content
 */
public interface Tree<T> {

    /**
     * @return the root of the org.misha.logical.tree
     */
    Node<T> root();

    /**
     * @return org.misha.logical.tree iterator
     */
    @SuppressWarnings("rawtypes")
    Iterator<Node> iterator();
}
