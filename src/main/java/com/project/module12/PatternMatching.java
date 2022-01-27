package com.project.module12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your implementations of the Boyer Moore string searching algorithm.
 */
public class PatternMatching {
    /**
     * Boyer Moore algorithm that relies on a last occurrence table.
     * This algorithm Works better with large alphabets.
     *
     * Make sure to implement the buildLastTable() method, which will be
     * used in this boyerMoore() method.
     *
     * Note: You may find the getOrDefault() method from Java's Map class useful.
     *
     * You may assume that the passed in pattern, text, and comparator will not be null.
     *
     * @param pattern    The pattern you are searching for in a body of text.
     * @param text       The body of text where you search for the pattern.
     * @param comparator You MUST use this to check if characters are equal.
     * @return           List containing the starting index for each match found.
     */
    public static List<Integer> boyerMoore(CharSequence pattern, CharSequence text, CharacterComparator comparator) {
        Map<Character, Integer> lastTable = buildLastTable(pattern);
        List<Integer> matchList = new ArrayList<>();
        int i = 0;
        int patternLength = pattern.length();
        int textLength = text.length();

        // We iterate until the end pattern (which is aligned with the
        // text at index i) "falls off" the end of the text.
        while (i <= textLength - patternLength) {
            int j = patternLength - 1;

            // We begin comparing characters in the text and the
            // pattern at the end of the pattern and its corresponding
            // index in the text.
            while (j >= 0 && charsMatch(text.charAt(i + j), pattern.charAt(j), comparator)) {
                j--;
            }

            // If the value of the index j is -1, then we have
            // matched all characters in the pattern and, thus, have
            // a full match. We add the index in the text of the start
            // of the matching sub-string to the output list and begin
            // searching again after shifting one character to the
            // right in the text.
            if (j == -1) {
                matchList.add(i);
                i++;
            } else {
                // If the value of j is not -1, then there was a
                // mismatch between the pattern and the current
                // location in the text.
                Character shiftChar = text.charAt(i + j);
                Integer shift = lastTable.getOrDefault(shiftChar, -1);

                // If the mismatched character is in the pattern and
                // its last occurrence in the pattern is to the left
                // of the mismatch, we shift the pattern to align the
                // pattern with the current section of the text.
                // Otherwise, we shift the pattern one character to
                // the right.
                if (shift < j) {
                    i = i + j - shift;
                } else {
                    i++;
                }
            }
        }

        return matchList;
    }

    /**
     * Builds the last occurrence table that will be used to run the Boyer Moore algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. pattern = octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     * You may assume that the passed in pattern will not be null.
     *
     * @param pattern A pattern you are building last table for.
     * @return A Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern.
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        Map<Character, Integer> lastTable = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            lastTable.put(pattern.charAt(i), i);
        }

        return lastTable;
    }

    /**
     * A helper method to determine if two characters are the same.
     *
     * @param a             One of the characters to compare.
     * @param b             One of the characters to compare.
     * @param comparator    Comparator object that determines if two characters are equal.
     * @return              Boolean indicator of whether the characters are the same.
     */

    private static boolean charsMatch(Character a, Character b, CharacterComparator comparator) {
        return comparator.compare(a, b) == 0;
    }
}
