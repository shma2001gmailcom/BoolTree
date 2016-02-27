package org.misha.logical.evaluator;

import org.misha.logical.tree.LogicalTree;
import org.misha.logical.tree.Node;
import org.misha.logical.tree.Tree;

import java.util.Collection;

/**
 * @author misha
 */
public abstract class StrictEvaluator implements Evaluator<boolean[]> {

    // returning "new boolean[2]" means the parsing error
    // works 2 times faster than BoolEvaluator
    private boolean[] evaluate(final Node<String> node) {
        if (node.isLeaf()) {
            return evaluateLeaf(node);
        } else {
            String op = node.getContent();
            if (op.trim().equalsIgnoreCase("OR")) {
                for (Node<String> child : node.getChildren()) {
                    boolean[] localResult = evaluate(child);
                    if (localResult.length != 1) {
                        return new boolean[2];
                    }
                    if (localResult[0]) {
                        return new boolean[]{true};
                    }
                }
                return new boolean[]{false};
            } else if (op.trim().equalsIgnoreCase("AND")) {
                for (Node<String> child : node.getChildren()) {
                    boolean[] localResult = evaluate(child);
                    if (localResult.length != 1) {
                        return new boolean[2];
                    }
                    if (!localResult[0]) {
                        return new boolean[]{false};
                    }
                }
                return new boolean[]{true};
            } else if (op.trim().equalsIgnoreCase("NOT")) {
                Collection<Node<String>> kindergarten = node.getChildren();
                if (kindergarten.size() != 1) {
                    return new boolean[2];
                } else {
                    Node<String> child = kindergarten.iterator().next();// exact
                    boolean[] localResult = evaluate(child);
                    if (localResult.length != 1) {
                        return new boolean[2];
                    } else {
                        return new boolean[]{!localResult[0]};
                    }
                }
            }
            return new boolean[2];
        }
    }

    @Override
    public boolean[] evaluate(final String s) {
        LogicalTree lt = new LogicalTree(s);
        Tree<String> tree = lt.makeTree();
        return evaluate(tree.root());
    }

    /**
     * @param s expression
     * @return boolean value of the expression
     */
    public boolean getValue(final String s) {
        boolean[] result = evaluate(s);
        if (result.length > 1) {
            throw new IllegalStateException("Some nodes has been evaluated wrong.");
        }
        return result[0];
    }

    /**
     * @param leaf the node which should be evaluated by business rules. This
     *             method ought to be overridden in subclasses to implement
     *             appropriate behaviour of org.misha.logical.evaluator.
     * @return result of concrete org.misha.logical.evaluator on elementary String which contains
     * not org.misha.logical operations.
     */
    public abstract boolean[] evaluateLeaf(final Node<String> leaf);
}
