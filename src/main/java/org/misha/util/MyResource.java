package org.misha.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * To redirect MyResource instance to some another resource, use
 * forget() first.
 * <p/>
 * MyResource can point to only one resource at the moment.
 */
public class MyResource {
    private static MyResource instance;
    private static Map<String, String> map = new HashMap<String, String>();

    private MyResource(String name) {
        ResourceBundle bundle = ResourceBundle.getBundle(name);
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = (String) bundle.getObject(key);
            map.put(key, value);
        }
    }

    @SuppressWarnings("javadoc")
    public static MyResource getResource(String name) {
        if (instance == null) {
            instance = new MyResource(name);
        }
        return instance;
    }

    /**
     * clears current resource to redirect the unique pointer to another
     * resource
     */
    public static void forget() {
        instance = null;
    }

    @SuppressWarnings("javadoc")
    public String get(String key) {
        return map.get(key);
    }
}
