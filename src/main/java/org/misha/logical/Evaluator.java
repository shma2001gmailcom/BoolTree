package org.misha.logical;

/**
 * Author: mshevelin Date: 8/6/12 Time: 12:30 PM
 * <p/>
 * Evaluates strings pretend to be an expression.
 *
 * @param <T> the type of return
 */

public interface Evaluator<T> {
    /**
     * @param expression the expression to evaluate
     * @return result of the evaluation
     * @throws Exception
     */
    T evaluate(String expression) throws Exception;
}
