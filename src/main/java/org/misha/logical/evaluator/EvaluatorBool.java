package org.misha.logical.evaluator;

import org.misha.logical.Evaluator;
import org.misha.logical.tree.LogicalTree;
import org.misha.logical.Node;
import org.misha.logical.Tree;

import java.util.Collection;

/**
 * @author misha
 */
@Deprecated
public class EvaluatorBool implements Evaluator<boolean[]> {

    // returning "new boolean[2]" means the parsing exception
    private boolean[] evaluate(final Node<String> node) {
        if (node.isLeaf()) {
            System.out.println("leaf= " + node);
            return new boolean[]{Boolean.parseBoolean(node.getContent())};
        } else {
            String op = node.getContent();
            System.out.println(node + "->" + node.getChildren());
            if (op.trim().equalsIgnoreCase("OR")) {
                for (Node<String> child : node.getChildren()) {
                    boolean[] localResult = evaluate(child);
                    if (localResult.length != 1) {
                        System.out.println(child + "->" + child.getChildren() + " * has wrong evaluate");
                        return new boolean[2];
                    }
                    if (evaluate(child)[0]) {
                        return new boolean[]{true};
                    }
                }
                return new boolean[]{false};
            } else if (op.trim().equalsIgnoreCase("AND")) {
                for (Node<String> child : node.getChildren()) {
                    boolean[] localResult = evaluate(child);
                    if (localResult.length != 1) {
                        System.out.println(child + "->" + child.getChildren() + " has wrong evaluate");
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
                    System.out.println("Too many children for 'NOT':" + kindergarten);
                    return new boolean[2];
                } else {
                    Node<String> child = kindergarten.iterator().next();
                    if (evaluate(child).length != 1) {
                        System.out.println(child + "has wrong evaluate");
                        return new boolean[2];
                    } else {
                        return new boolean[]{!evaluate(child)[0]};
                    }
                }
            }
            System.out.println(node);
            return new boolean[2];
        }
    }

    @Override
    public boolean[] evaluate(final String s) {
        LogicalTree lt = new LogicalTree(s);
        Tree<String> tree = lt.makeTree();
        System.out.println(tree.toString());
        return evaluate(tree.root());
    }
}
