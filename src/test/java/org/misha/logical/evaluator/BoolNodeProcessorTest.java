package org.misha.logical.evaluator;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.misha.logical.Node;

import java.security.SecureRandom;
import java.util.Calendar;

import static junit.framework.Assert.assertTrue;

/**
 * Author: mshevelin Date: 7/17/12 Time: 5:48 PM
 */
public class BoolNodeProcessorTest {
    private static final Logger log = Logger.getLogger(BoolNodeProcessorTest.class);
    private static final int ITERATIONS = 1000000;

    /**
     * The expression !((!x || y) && (!y || z)) || (!x || z) is true for each
	 * boolean x, y, z.
	 */
    @Test
    public void testEvaluate() throws Exception {
        boolean result = true;
        BoolNodeProcessor e = new BoolNodeProcessor() {

            @Override
            public boolean evaluateLeaf(Node<String> leaf) {
                return Boolean.valueOf(leaf.getContent());
            }
        };
        long begin = Calendar.getInstance().getTimeInMillis();
        final SecureRandom random = new SecureRandom();
        for (int i = 0; i < ITERATIONS; ++i) {
            String xS = String.valueOf(random.nextBoolean());
            xS = String.format("(%s)", xS);
            String yS = String.valueOf(random.nextBoolean());
            yS = String.format("(%s)", yS);
            String zS = String.valueOf(random.nextBoolean());
            zS = String.format("(%s)", zS);
            // x ==> y
            String xy = String.format("((%sNOT)%sOR)", xS, yS);
            // y ==> z
            String yz = String.format("((%sNOT)%sOR)", yS, zS);
            // x ==> z
            String xz = String.format("((%sNOT)%sOR)", xS, zS);
            // (x ==> y) & (y ==> z)
            String xyandyz = String.format("(%s%sAND)", xy, yz);
            // ((x ==> y) & (y ==> z)) ==> (x ==> z)
            String xyandyzimpliesxz = String.format("((%sNOT)%sOR)", xyandyz, xz);
            boolean result1 = e.evaluate(xyandyzimpliesxz);
            result &= result1;
        }
        long end = Calendar.getInstance().getTimeInMillis() - begin;
        log.info(String.format("%s\n Estimate iteration time is %s ms", result, (double) end / ((double) ITERATIONS)));
        assertTrue(result);
    }
}
