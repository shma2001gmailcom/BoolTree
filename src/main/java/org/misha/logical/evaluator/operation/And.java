package org.misha.logical.evaluator.operation;

import org.misha.logical.evaluator.Operation;

import java.util.LinkedList;

import static org.misha.logical.evaluator.operation.Or.or;

/**
 * Author: mshevelin Date: 2/2/12 Time: 5:07 PM
 * <p/>
 * Operation and.
 *
 * The value
 */

public final class And implements Operation<Boolean, Boolean> {

    private And() {
    }

    /**
     * @return operation and
     */
    public static And and() {
        return Holder.and;
    }

    @Override
    public Boolean proceed(final LinkedList<Boolean> args) throws IllegalArgumentException {
        LinkedList<Boolean> anotherArgs = new LinkedList<Boolean>();
        for (Boolean x : args) {
            if (x == null) {
                throw new IllegalArgumentException("Null arguments is not supported.");
            }
            anotherArgs.add(!x);
        }
        return !or().proceed(anotherArgs);
    }

    private static class Holder {
        private static final And and = new And();
    }
}
