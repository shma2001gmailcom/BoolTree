package org.misha.logical.operation;

import org.misha.logical.Operation;

import java.util.LinkedList;

/**
 * Author: mshevelin Date: 2/2/12 Time: 5:07 PM
 * <p/>
 * Operation or.
 * The value
 */

public final class Or implements Operation<Boolean, Boolean> {

    private Or() {
    }

    public static Or or() {
        return Holder.or;
    }

    @Override
    public Boolean proceed(final LinkedList<Boolean> args) throws IllegalArgumentException {
        if (args.size() == 0) {
            throw new IllegalArgumentException("OR must have at least one argument.");
        }
        for (final Boolean x : args) {
            if (x == null) {
                throw new IllegalArgumentException("Null arguments is not supported.");
            }
            if (x) {
                return true;
            }
        }
        return false;
    }

    private static class Holder {
        private static final Or or = new Or();
    }
}
