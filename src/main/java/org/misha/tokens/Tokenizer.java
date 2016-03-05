package org.misha.tokens;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Author: mshevelin Date: 2/2/12 Time: 9:30 AM
 */
public class Tokenizer {
    private final LinkedList<Character> list = new LinkedList<Character>();
    private final Set<String> tokens = new HashSet<String>();
    private final LinkedList<String> sawn = new LinkedList<String>();

    /**
     * @param s the string to saw on org.misha.tokens
     */
    public Tokenizer(final String s) {
        setInput(s);
    }

    /**
     * @return list of org.misha.tokens
     */
    public LinkedList<String> getSawn() {
        return sawn;
    }

    /**
     * @param s the string to saw on org.misha.tokens
     */
    public void setInput(final String s) {
        list.clear();
        for (int i = 0; i < s.length(); ++i) {
            list.push(s.charAt(i));
        }
    }

    /**
     * @param s new token to add to list of org.misha.tokens
     * @return this
     */
    public Tokenizer addToken(String s) {
        tokens.add(s);
        return this;
    }

    /**
     * @param s the string to check
     * @return boolean
     */
    public boolean isToken(final String s) {
        return tokens.contains(s);
    }

    /**
     * saws a string on org.misha.tokens
     */
    public void saw() {
        StringBuffer sb = new StringBuffer();
        String s;
        while (!list.isEmpty()) {
            char c = list.removeLast();
            sb = sb.append(c);
            if (isToken(s = sb.toString())) {
                sawn.addLast(s);
                sb = sb.delete(0, sb.length());
            }
        }
    }
}
