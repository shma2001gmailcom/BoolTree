package org.misha.logical.tree.impl;

import org.junit.Test;
import org.misha.logical.Node;
import org.misha.logical.Tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NodeImplTest {

    @Test
    public void testCopy() throws Exception {
        Node<Integer> root = new NodeImpl<Integer>(1);
        root.addChild(new NodeImpl<Integer>(10));
        root.addChild(new NodeImpl<Integer>(11));
        Tree<Integer> t = new TreeImpl<Integer>(root);
        Node<Integer> copyRoot = root.copy();
        Tree<Integer> copyT = new TreeImpl<Integer>(copyRoot);
        assertTrue(t.toString().equals(copyT.toString()));
        assertFalse(t == copyT);
    }
}