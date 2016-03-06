package org.misha.logical.operation;

import org.misha.logical.Operation;

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

    public static Not not() {
        return Holder.not;
    }

    @Override
    public Boolean proceed(final LinkedList<Boolean> args) throws IllegalArgumentException {
        if (args.size() != 1) {
            throw new IllegalArgumentException("NOT must have exactly one argument.");
        }
        final Boolean value = args.iterator().next();
        if (value == null) {
            throw new IllegalArgumentException("Null arguments is not supported.");
        }
        return !value;
    }

    private static class Holder {
        private static final Not not = new Not();
    }
}
