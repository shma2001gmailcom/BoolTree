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
            add(0);
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
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
            add(0);
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(100);
            add(7);
            add(737);
            add(777);
            add(787);
        }};
        final ExampleRanges example = new ExampleRanges("(((10 1) NOT) ((97-102) ( 0 - 7 ) (73 7    -787) OR) AND)");
        for (Integer integer : integers) {
            example.search(integer);
        }
        for (Integer i : example) {
            assertTrue(expected.contains(i) && expected.size() == example.size());
        }
    }
}