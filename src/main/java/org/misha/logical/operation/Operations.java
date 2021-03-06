package org.misha.logical.operation;

import org.misha.logical.Operation;

import java.util.HashMap;
import java.util.Map;

/**
 * author: misha
 * date: 2/28/16 10:50 AM.
 */
public enum Operations {
    OPERATIONS;

    private static final Map<String, Operation<Boolean, Boolean>> operations =
            new HashMap<String, Operation<Boolean, Boolean>>() {{
                put("AND", And.and());
                put("OR", Or.or());
                put("NOT", Not.not());
            }};

    public Operation<Boolean, Boolean> get(String name) {
        return operations.get(name.toUpperCase());
    }
}
