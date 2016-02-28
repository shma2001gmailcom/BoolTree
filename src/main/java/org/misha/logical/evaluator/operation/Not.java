package org.misha.logical.evaluator.operation;

import org.misha.logical.evaluator.Operation;

import java.util.LinkedList;

/**
 * Author: mshevelin Date: 2/2/12 Time: 5:07 PM
 * <p/>
 * Operation not.
 * The value
 */

public final class Not implements Operation<Boolean, Boolean> {

    private Not() {
    }

    /**
     * @return operation not
     */
    public static Not not() {
        return Holder.not;
    }

    @Override
    public Boolean proceed(LinkedList<Boolean> args) throws IllegalArgumentException {
        if (args.size() != 1) {
            throw new IllegalArgumentException("NOT must have exactly one argument.");
        }
        Boolean value = args.iterator().next();
        if (value == null) {
            throw new IllegalArgumentException("Null arguments is not supported.");
        }
        return !value;
    }

    private static class Holder {
        private static final Not not = new Not();
    }
}
