package org.misha.logical.evaluator;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.Calendar;

import static junit.framework.Assert.assertTrue;

/**
 * @author misha
 */
public class BoolEvaluatorTest {
    private static final Logger log = Logger.getLogger(BoolEvaluatorTest.class);
    private static final int ITERATIONS = 100000;

    @SuppressWarnings("javadoc")
    @Test
    public void testEvaluate() throws Exception {
        boolean result = true;
        Evaluator<Boolean> e = new BoolEvaluator();
        long begin = Calendar.getInstance().getTimeInMillis();
        final SecureRandom random = new SecureRandom();
        for (int i = 0; i < ITERATIONS; ++i) {
            String xS = String.valueOf(random.nextBoolean());
            xS = "(" + xS + ")";
            String yS = String.valueOf(random.nextBoolean());
            yS = "(" + yS + ")";
            String zS = String.valueOf(random.nextBoolean());
            zS = "(" + zS + ")";
            // !((!x || y) && (!y || z)) || (!x || z) is true for each
            // boolean x, y, z.
            String xy = "((" + xS + "NOT)" + yS + "OR)";// !x||y
            String yz = "((" + yS + "NOT)" + zS + "OR)";// !y||z
            String xz = "((" + xS + "NOT)" + zS + "OR)";// !x||z
            String xyandyz = "(" + xy + yz + "AND)";
            String xyandyzimpliesxz = "((" + xyandyz + "NOT)" + xz + "OR)";
            result &= e.evaluate(xyandyzimpliesxz);
        }
        long end = Calendar.getInstance().getTimeInMillis() - begin;
        log.info(result + "\n Estimate iteration time =" + ((double) end / ((double) ITERATIONS)));
        log.info(result);
        assertTrue(result);
    }
}
