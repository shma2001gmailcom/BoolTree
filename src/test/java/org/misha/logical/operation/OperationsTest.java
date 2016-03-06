package org.misha.logical.operation;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.misha.logical.operation.Operations.OPERATIONS;

public class OperationsTest {

    @Test
    public void testGet() {
        assertNotNull(OPERATIONS.get("OR"));
        assertNotNull(OPERATIONS.get("AND"));
        assertNotNull(OPERATIONS.get("NOT"));
    }
}