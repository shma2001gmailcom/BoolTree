package org.misha.logical.evaluator;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.misha.logical.Node;

import java.security.SecureRandom;
import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Author: mshevelin Date: 7/17/12 Time: 5:48 PM
 */
public class StrictEvaluatorTest {
    private static final Logger log = Logger.getLogger(StrictEvaluatorTest.class);
    private static final int ITERATIONS = 1000000;

    /*
     * The expression !((!x || y) && (!y || z)) || (!x || z) is true for each
	 * boolean x, y, z.
	 */
    @SuppressWarnings("javadoc")
    @Test
    public void testEvaluate() {
        boolean result = true;
        StrictEvaluator e = new StrictEvaluator() {

            @Override
            public boolean[] evaluateLeaf(Node<String> leaf) {
                return new boolean[]{Boolean.valueOf(leaf.getContent())};
            }
        };
        long begin = Calendar.getInstance().getTimeInMillis();
        final SecureRandom random = new SecureRandom();
        for (int i = 0; i < ITERATIONS; ++i) {
            // log.info("              " + i);
            String xS = String.valueOf(random.nextBoolean());
            xS = "(" + xS + ")";
            String yS = String.valueOf(random.nextBoolean());
            yS = "(" + yS + ")";
            String zS = String.valueOf(random.nextBoolean());
            zS = "(" + zS + ")";
            // x ==> y
            String xy = "((" + xS + "NOT)" + yS + "OR)";
            // y ==> z
            String yz = "((" + yS + "NOT)" + zS + "OR)";
            // x ==> z
            String xz = "((" + xS + "NOT)" + zS + "OR)";
            // (x ==> y) & (y ==> z)
            String xyandyz = "(" + xy + yz + "AND)";
            // ((x ==> y) & (y ==> z)) ==> (x ==> z)
            String xyandyzimpliesxz = "((" + xyandyz + "NOT)" + xz + "OR)";
            boolean[] result1 = e.evaluate(xyandyzimpliesxz);
            assertEquals(result1.length, 1);
            result &= result1[0];
        }
        long end = Calendar.getInstance().getTimeInMillis() - begin;
        log.info("Estimate iteration time=" + ((double) end / ((double) ITERATIONS)));
        assertTrue(result);
    }
}
