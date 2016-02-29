package org.misha.logical.evaluator;

import org.misha.logical.Evaluator;
import org.misha.logical.tree.LogicalTree;
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
        return new BoolNode((NodeImpl<String>) new LogicalTree(expression).makeTree().root()).evaluate();
    }
}
