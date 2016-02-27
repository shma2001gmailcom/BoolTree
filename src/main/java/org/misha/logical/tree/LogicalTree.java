package org.misha.logical.tree;

import org.apache.log4j.Logger;
import org.misha.logical.tree.impl.NodeImpl;
import org.misha.logical.tree.impl.TreeImpl;

import java.util.LinkedList;
import java.util.List;
/**
 * Author: misha shevelin
 * Date: 1/28/12
 * Time: 7:16 PM
 */

/**
 * Given some correct org.misha.logical expression, this class produces the org.misha.logical.tree for
 * evaluating it. This class DOES NOT really evaluates the expression.
 * <p/>
 * THE RULES FOR BUILDING THE CORRECT EXPRESSION
 * <p/>
 * Let x, y, z be correct org.misha.logical expressions. Then the next (and similar)
 * org.misha.logical expressions also are correct.
 * <p/>
 * 0. Any letter is a correct expression.
 * <p/>
 * 1. The negation of x. Expression is true iff x is false: ((x) NOT);
 * <p/>
 * 2. At least one of x, y, z. Expression is true iff one of x, y, z is true:
 * ((x) (y) (z) OR);
 * <p/>
 * 3. All of x, y, z. Expression is true iff all x, y, z are true: ((x) (y) (z)
 * AND);
 * <p/>
 * Note that each parenthesis has a meaning.
 */
public class LogicalTree {
    private static final Logger log = Logger.getLogger(LogicalTree.class);
    private final List<Character> list = new LinkedList<Character>();

    /**
     * @param expression the expression to build evaluation org.misha.logical.tree
     */
    public LogicalTree(final CharSequence expression) {
        for (int i = 0; i < expression.length(); ++i) {
            list.add(expression.charAt(i));
        }
    }

    // checks are parenthesises correct
    private boolean parCorrect() {
        int level = 0;
        for (Character c : list) {
            if (c == ')') {
                level--;
            } else if (c == '(') {
                level++;
            }
        }
        return level == 0;
    }

    /**
     * @return evaluation org.misha.logical.tree
     */
    public Tree<String> makeTree() {
        if (!parCorrect()) {
            log.error("parenthesizes does not match. check input.");
            return null;
        }
        Node<String> node = null;
        Node<String> parent = null;
        String s = "";
        while (!list.isEmpty()) {
            char c = list.remove(0);
            if (c == '(') {
                node = new NodeImpl<String>(s);
                if (parent != null) {
                    parent.addChild(node);
                }
                parent = node;
            } else if (c == ')') {
                if (parent != null) {
                    parent.setContent(s);
                    parent.setName(s); // for String.equals() test control
                    if (parent.getParent() != null) {
                        parent = parent.getParent();
                    }
                }
                s = "";// clear s
            } else {
                if (c != ' ') {
                    s += c;
                }
            }
        }
        if (node != null) {
            while (!node.isRoot()) {
                node = node.getParent();// achieve the root
            }
        }
        return new TreeImpl<String>(node);
    }
}
