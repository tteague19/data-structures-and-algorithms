package com.project.module10;

import static org.junit.Assert.assertArrayEquals;

import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SortingTests {
    @Test
    public void testBubbleSort() {
        Integer[] actuals = new Integer[]{1, 2, 6, 5, 3, 4, 7, 8, 9};
        Integer[] expecteds = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        IntegerComparator comparator = new IntegerComparator();
        Sorting.bubbleSort(actuals, comparator);

        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testBubbleSortDuplicates() {
        Integer[] actuals = new Integer[]{1, 2, 6, 5, 3, 6, 7, 8, 9};
        Integer[] expecteds = new Integer[]{1, 2, 3, 5, 6, 6, 7, 8, 9};
        IntegerComparator comparator = new IntegerComparator();
        Sorting.bubbleSort(actuals, comparator);

        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testInsertionSort() {
        Integer[] actuals = new Integer[]{6, 4, 2, 5, 3, 7, 6, 8, 9, 10};
        Integer[] expecteds = new Integer[]{2, 3, 4, 5, 6, 6, 7, 8, 9, 10};
        IntegerComparator comparator = new IntegerComparator();
        Sorting.insertionSort(actuals, comparator);

        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testSelectionSort() {
        Integer[] actuals = new Integer[]{5, 7, 8, 9, 3, 6, 4, 6, 2, 10};
        Integer[] expecteds = new Integer[]{2, 3, 4, 5, 6, 6, 7, 8, 9, 10};
        IntegerComparator comparator = new IntegerComparator();
        Sorting.selectionSort(actuals, comparator);

        assertArrayEquals(expecteds, actuals);
    }
}
