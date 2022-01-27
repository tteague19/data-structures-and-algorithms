package com.project.module12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.junit.Test;
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

    @Test
    public void testLastTableEmpty() {
        Map<Character, Integer> expected = new HashMap<>();

        CharSequence pattern = "";

        Map<Character, Integer> actual = PatternMatching.buildLastTable(pattern);
        assertEquals(expected, actual);
    }

    @Test
    public void testBoyerMooreSingleMatch() {
        List<Integer> expected = new ArrayList<>(Arrays.asList(11));

        CharSequence pattern = "happy";
        CharSequence text = "because im happy";
        CharacterComparator comparator = new CharacterComparator();

        List<Integer> actual = PatternMatching.boyerMoore(pattern, text, comparator);
        assertEquals(expected, actual);
    }

    @Test
    public void testBoyerMooreMultipleMatch() {
        List<Integer> expected = new ArrayList<>(Arrays.asList(9, 27));

        CharSequence pattern = "happy";
        CharSequence text = "why am i happy? because im happy.";
        CharacterComparator comparator = new CharacterComparator();

        List<Integer> actual = PatternMatching.boyerMoore(pattern, text, comparator);
        assertEquals(expected, actual);
    }

    @Test
    public void testBoyerMooreNoMatch() {
        List<Integer> expected = new ArrayList<>();

        CharSequence pattern = "happy";
        CharSequence text = "there is no path to happiness. happiness is the path.";
        CharacterComparator comparator = new CharacterComparator();

        List<Integer> actual = PatternMatching.boyerMoore(pattern, text, comparator);
        assertEquals(expected, actual);
    }

    @Test
    public void testBoyerMooreLongPattern() {
        List<Integer> expected = new ArrayList<>();

        CharSequence pattern = "happy";
        CharSequence text = "sad";
        CharacterComparator comparator = new CharacterComparator();

        List<Integer> actual = PatternMatching.boyerMoore(pattern, text, comparator);
        assertEquals(expected, actual);
        assertEquals(comparator.getComparisonCount(), 0);
    }
}