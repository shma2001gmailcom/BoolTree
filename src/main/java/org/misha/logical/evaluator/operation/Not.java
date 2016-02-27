package org.misha.logical.evaluator.operation;

import org.misha.logical.evaluator.Operation;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Author: mshevelin Date: 2/2/12 Time: 5:07 PM
 * <p/>
 * Operation not.
 */

public final class Not implements Operation<Boolean, Boolean> {
    private static Not not;

    private Not() {
    }

    /**
     * @return operation not
     */
    public static Not NOT() {
        if (not == null) {
            not = new Not();
        }
        return not;
    }

    @Override
    public Boolean proceed(LinkedList<Boolean> args) throws IllegalArgumentException {
        if (args.size() != 1) {
            throw new IllegalArgumentException("NOT must have exactly one argument.");
        }
        Iterator<Boolean> it = args.iterator();
        Boolean value = it.next();
        if (value == null) {
            throw new IllegalArgumentException("Null arguments is not supported.");
        }
        return !value;
    }
}
