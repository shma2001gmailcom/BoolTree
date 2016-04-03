package org.misha.example;

import org.misha.logical.Node;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.remove;

/**
 * author: misha
 * date: 3/27/16
 * time: 2:05 PM
 */
final class Range {
    private static final String UNSIGNED_INT =
            "([0 ]|1[0-9 ]*|2[0-9 ]*|3[0-9 ]*|4[0-9 ]*|5[0-9 ]*|6[0-9 ]*|7[0-9 ]*|8[0-9 ]*|9[0-9 ]*)";
    static final Pattern RANGE_PATTERN = Pattern.compile(String.format("%s-%s", UNSIGNED_INT, UNSIGNED_INT));
    private static final Pattern UNSIGNED_INT_PATTERN = Pattern.compile(UNSIGNED_INT);
    private final String range;

    Range(final Node<String> leaf) {
        range = remove(leaf.getContent(), " ");
    }

    boolean contains(final int i) {
        final Matcher rangeMatcher = RANGE_PATTERN.matcher(range);
        if (!rangeMatcher.find()) {
            final Matcher unsignedIntMatcher = UNSIGNED_INT_PATTERN.matcher(range);
            if (!unsignedIntMatcher.find()) {
                throw new IllegalArgumentException("is not a range: " + range);
            } else {
                return i == Integer.parseInt(unsignedIntMatcher.group(1));
            }
        } else {
            final int low = Integer.parseInt(rangeMatcher.group(1));
            final int high = Integer.parseInt(rangeMatcher.group(2));
            if (low > high) {
                throw new IllegalArgumentException("is not a range: " + range);
            } else {
                return low <= i && i <= high;
            }
        }
    }
}
