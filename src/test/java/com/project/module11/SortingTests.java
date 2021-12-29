package com.project.module11;

import static org.junit.Assert.assertArrayEquals;

import java.util.Comparator;

import org.junit.Test;

public class SortingTests {

    @Test
    public void testMergeSort() {
        Integer[] actuals = new Integer[]{1, 2, 6, 5, 3, 6, 7, 8, 9};
        Integer[] expecteds = new Integer[]{1, 2, 3, 5, 6, 6, 7, 8, 9};
        IntegerComparator comparator = new IntegerComparator();
        Sorting.mergeSort(actuals, comparator);

        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testLSDRadixSort() {
        int[] actuals = new int[]{17, 743, 672, 780, 917, 743, 623, 288, 432, 281, 76};
        int[] expecteds = new int[]{17, 76, 281, 288, 432, 623, 672, 743, 743, 780, 917};
        Sorting.lsdRadixSort(actuals);

        assertArrayEquals(expecteds, actuals);
    }
}
