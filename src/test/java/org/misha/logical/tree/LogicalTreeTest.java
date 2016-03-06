package org.misha.logical.tree;

import junit.framework.TestCase;

/**
 * Author: misha shevelin Date: 1/29/12 Time: 4:44 PM
 */

public class LogicalTreeTest extends TestCase {
    private String s;
    private String expected1;
    private String expected2;
    private String expected3;
    private String expected;

    @Override
    public void setUp() throws Exception {
        expected1 = "NOT->[a]";
        expected2 = "RESULT->[OR->[a, b, c]]";
        expected3 = "RESULT->[AND->[a, b, c]]";
        expected = "RESULT->[NOT->[OR->[x, y, z]], NOT->[OR->[c, AND->[NOT->[a], b]]]]";
    }

    @Override
    public void tearDown() throws Exception {
        s = null;
    }

    @SuppressWarnings("javadoc")
    public void testMakeTree1() throws Exception {
        s = "((a) NOT)";
        LogicalTree bt1 = new LogicalTree(s);
        String actual = bt1.makeTree().toString().trim();
        assertEquals(actual, expected1);
    }

    @SuppressWarnings("javadoc")
    public void testMakeTree2() throws Exception {
        s = "(RESULT)((a) (b) (c) OR)";
        LogicalTree bt2 = new LogicalTree(s);
        String actual = bt2.makeTree().toString().trim();
        assertEquals(actual, expected2);
    }

    @SuppressWarnings("javadoc")
    public void testMakeTree3() throws Exception {
        s = "(RESULT)((a) (b) (c) AND)";
        LogicalTree bt3 = new LogicalTree(s);
        String actual = bt3.makeTree().toString().trim();
        assertEquals(actual, expected3);
    }

    @SuppressWarnings("javadoc")
    public void testMakeTree() throws Exception {
        s = "(RESULT)" + "(" + "   ((x) (y) (z) OR) NOT) " + "   (((c) (((a) NOT) (b) AND) OR) " + "  NOT" + ")";
        LogicalTree bt = new LogicalTree(s);
        String actual = bt.makeTree().toString().trim();
        assertEquals(actual, expected);
    }

    @SuppressWarnings("javadoc")
    public void testMakeTreeStepByStep() {
        String s1 = "((x) NOT)";
        String s2 = "((y)(z) OR)";
        String s3 = "(" + s1 + s2 + "AND" + ")";
        String result = "(RESULT)" + s3;
        LogicalTree btSbS = new LogicalTree(result);
        String actual = btSbS.makeTree().toString().trim();
        assertEquals(actual, "RESULT->[AND->[NOT->[x], OR->[y, z]]]");
    }
}
