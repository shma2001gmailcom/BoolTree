package org.misha.logical;

import java.util.LinkedList;

/**
 * Author: mshevelin
 * Date: 7/18/12
 * Time: 12:50 PM
 *
 * @param <T> the type of return
 * @param <S> the type of all arguments
 */
public interface Operation<T, S> {
    /**
     * @param arguments argument-list
     * @return result of operation
     */
    T proceed(LinkedList<S> arguments);
}
