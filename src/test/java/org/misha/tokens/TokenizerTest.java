package org.misha.tokens;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Author: mshevelin
 * Date: 7/18/12
 * Time: 10:31 AM
 */
public class TokenizerTest {
    @SuppressWarnings("javadoc")
    @Test
    public void testSaw() {
        String expected =
                "(\n" + "\n" + "(\n" + "\n" + "a\n" + "\n" + ")\n" + "\n" + "(\n" + "\n" + "(\n" + "\n" + "b\n" + "\n" + ")\n" + "\n" + "(\n" + "\n" + "c\n" + "\n" + ")\n" + "\n" + ")\n" + "\n" + ")";
        String input = "((a)((b)(c)))";
        Tokenizer t = new Tokenizer(input);
        t.addToken("(").addToken(")").addToken("a").addToken("b").addToken("c").saw();
        StringBuilder sb = new StringBuilder();
        for (String s1 : t.getSawn()) {
            sb = sb.append("\n").append(s1).append("\n");
        }
        String actual = sb.toString();
        actual = actual.substring(1, actual.length() - 1);
        assertEquals(actual, expected);
    }
}
