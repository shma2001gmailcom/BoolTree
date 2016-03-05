package org.misha.logical.evaluator;

import org.misha.logical.Evaluator;
import org.misha.logical.Node;
import org.misha.logical.operation.Operations;
import org.misha.logical.tree.LogicalTree;

import java.util.LinkedList;

/**
 * @author misha
 */
public abstract class BoolNodeProcessor implements Evaluator<Boolean> {

    private boolean evaluate(final Node<String> node) {
        if (node.isLeaf()) {
            return evaluateLeaf(node);
        } else {
            LinkedList<Boolean> args = new LinkedList<Boolean>();
            for (Node<String> child : node.getChildren()) {
                args.add(evaluate(child));
            }
            return Operations.OPERATIONS.findBy(node.getContent()).proceed(args);
        }
    }

    @Override
    public Boolean evaluate(final String s) {
        return evaluate(new LogicalTree(s).makeTree().root());
    }

    /**
     * This method should be overridden to implement
     * appropriate behaviour of org.misha.logical.evaluator.
     *
     * @param leaf the node which should be evaluated by business rules.
     * @return result of concrete org.misha.logical.evaluator on elementary String which contains
     * not org.misha.logical operations.
     */
    public abstract boolean evaluateLeaf(final Node<String> leaf);
}
