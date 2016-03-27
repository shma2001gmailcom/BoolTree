package org.misha.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Selects integers satisfying a condition expressed in terms of the 'ranges'.
 */
public class ExampleRangesTest {

    @Test
    public void testSearch() throws Exception {
        final Set<Integer> integers = new HashSet<Integer>() {{
            add(7);
            add(8);
            add(25);
            add(7);
            add(8);
            add(25);
            add(100);
            add(101);
            add(787);
            add(777);
            add(737);
        }};
        final List<Integer> expected = new ArrayList<Integer>() {{
            add(100);
            add(7);
            add(737);
            add(777);
            add(787);
        }};
        final ExampleRanges example = new ExampleRanges("(((101) NOT) ((97-102) (1-7) (737-787) OR) AND)");
        for (Integer integer : integers) {
            example.search(integer);
        }
        for (Integer exampleRange : example) {
            assertTrue(expected.contains(exampleRange) && expected.size() == example.size());
        }
    }
}