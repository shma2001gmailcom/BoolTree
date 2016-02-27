package org.misha.logical.tree;

/**
 * Author: mshevelin Date: 12/29/11 Time: 11:07 AM
 *
 * @param <T> type of next
 */
public interface Iterator<T> {

    /**
     * @return boolean
     */
    boolean hasNext();

    /**
     * @return next
     */
    T next();
}
