package com.project.module12;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PatternMatchingTests {

    @Test
    public void testLastTable() {
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('a', 4);
        expected.put('b', 5);
        expected.put('c', 3);

        CharSequence pattern = "abacab";

        Map<Character, Integer> actual = PatternMatching.buildLastTable(pattern);
        assertEquals(expected, actual);
    }
}