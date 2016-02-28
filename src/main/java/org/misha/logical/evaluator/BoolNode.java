package org.misha.logical.evaluator;

import org.misha.logical.tree.Node;
import org.misha.logical.tree.impl.NodeImpl;

import java.util.LinkedList;

import static org.misha.logical.evaluator.operation.Operations.OPERATIONS;

/**
 * Author: mshevelin Date: 2/2/12 Time: 5:07 PM
 * <p/>
 * The node of the org.misha.logical.tree modelling the calculation process.
 */

public class BoolNode extends NodeImpl<String> {
    private Operation<Boolean, Boolean> op;

    /**
     * @param node the boolean node constructed by the given node
     */
    public BoolNode(NodeImpl<String> node) {
        super(node.getContent());
        for (Node<String> child : node.getChildren()) {
            addChild(new BoolNode((NodeImpl<String>) child));
        }
        op = OPERATIONS.findBy(getContent());
    }

    /**
     * @return the boolean value of the boolean node
     * @throws Exception if proceed() throws
     */
    public boolean evaluate() throws Exception {
        if (isLeaf()) {
            return evaluateLeaf();
        }
        LinkedList<Boolean> args = new LinkedList<Boolean>();
        for (Node<String> child : getChildren()) {
            args.add(new BoolNode((NodeImpl<String>) child).evaluate());
        }
        return op.proceed(args);
    }

    /**
     * @return boolean value of the string expression contained in the leaf.
     * <p/>
     * This method ought to be overridden in subclasses for special
     * cases of usage.
     */
    public boolean evaluateLeaf() {
        return Boolean.parseBoolean(this.getContent());
    }
}
