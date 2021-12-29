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

    // @Test
    // public void testLSDRadixSort() {
    //     Integer[] actuals = new Integer[]{5, 7, 8, 9, 3, 6, 4, 6, 2, 10};
    //     Integer[] expecteds = new Integer[]{2, 3, 4, 5, 6, 6, 7, 8, 9, 10};
    //     IntegerComparator comparator = new IntegerComparator();
    //     Sorting.lsdRadixSort(actuals, comparator);

    //     assertArrayEquals(expecteds, actuals);
    // }
}
