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
}
