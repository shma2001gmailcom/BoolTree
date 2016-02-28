package org.misha.logical.evaluator;

import org.junit.Test;
import org.misha.logical.evaluator.operation.And;
import org.misha.logical.evaluator.operation.Or;

import java.util.LinkedList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Author: mshevelin Date: 8/9/12 Time: 2:36 PM
 */
public class OperationsTest {
    @SuppressWarnings("javadoc")
    @Test
    public void testOperations() {
        LinkedList<Boolean> args = new LinkedList<Boolean>();
        args.add(true);
        args.add(false);
        assertTrue(Or.or().proceed(args));
        assertFalse(And.and().proceed(args));
        args.clear();
        args.add(true);
        args.add(true);
        assertTrue(Or.or().proceed(args));
        assertTrue(And.and().proceed(args));
        args.clear();
        args.add(false);
        args.add(false);
        assertFalse(Or.or().proceed(args));
        assertFalse(And.and().proceed(args));
    }
}
