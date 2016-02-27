package org.misha.logical.evaluator;

import org.misha.logical.tree.LogicalTree;
import org.misha.logical.tree.Tree;
import org.misha.logical.tree.impl.NodeImpl;

/**
 * Author: mshevelin Date: 2/2/12 Time: 5:07 PM
 * <p/>
 * Name tells.
 */

public class BoolEvaluator implements Evaluator<Boolean> {

    // works 2 times slower than EvaluatorBool
    @Override
    public Boolean evaluate(final String expression) throws Exception {
        LogicalTree lt = new LogicalTree(expression);
        Tree<String> tree = lt.makeTree();
        BoolNode boolNode = new BoolNode((NodeImpl<String>) tree.root());
        return boolNode.evaluate();
    }
}
