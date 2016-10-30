package org.misha.example;

import org.misha.logical.Node;
import org.misha.logical.evaluator.BoolNodeProcessor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * author: misha
 * date: 3/27/16
 * time: 11:38 AM
 */
final class ExampleRanges implements Iterable<Integer> {
    private final Set<Integer> result = new HashSet<Integer>();
    private final String expression;

    ExampleRanges(final String expr) {
        expression = expr;
    }

    private BoolNodeProcessor evaluator(final int i) {
        return new BoolNodeProcessor() {

            @Override
            public boolean evaluateLeaf(final Node<String> leaf) {
                return new Range(leaf).contains(i);
            }
        };
    }

    void search(final int i) {
        if (evaluator(i).evaluate(expression)) {
            result.add(i);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return result.iterator();
    }

    int size() {
        return result.size();
    }
}
