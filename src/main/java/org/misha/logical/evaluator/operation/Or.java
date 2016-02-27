package org.misha.logical.evaluator.operation;

import org.misha.logical.evaluator.Operation;

import java.util.LinkedList;

/**
 * Author: mshevelin Date: 2/2/12 Time: 5:07 PM
 * <p/>
 * Operation or.
 */

public final class Or implements Operation<Boolean, Boolean> {
    private static Or or;

    private Or() {
    }

    /**
     * @return operation or
     */
    public static Or OR() {
        if (or == null) {
            or = new Or();
        }
        return or;
    }

    @Override
    public Boolean proceed(LinkedList<Boolean> args) throws IllegalArgumentException {
        if (args.size() == 0) {
            throw new IllegalArgumentException("OR must have at least one argument.");
        }
        for (Boolean x : args) {
            if (x == null) {
                throw new IllegalArgumentException("Null arguments is not supported.");
            }
            if (x) {
                return true;
            }
        }
        return false;
    }
}
