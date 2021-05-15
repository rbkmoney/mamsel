package com.rbkmoney.mamsel;

public class StringUtils {

    private StringUtils() {
    }

    /**
     * Checks if string is null or empty.
     *
     * @param s input string
     */
    static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
