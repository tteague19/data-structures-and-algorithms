package com.project.module11;

import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class Sorting {

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        // In this instance, we have reached the base case.
        if (arr.length <= 1) {
            return;
        }

        int midIndex = arr.length / 2;
        T[] leftArr = (T[]) new Object[midIndex];
        T[] rightArr = (T[]) new Object[arr.length - midIndex];

        for (int copyIndex = 0; copyIndex < arr.length; copyIndex++) {
            if (copyIndex < midIndex) {
                leftArr[copyIndex] = arr[copyIndex];
            } else {
                rightArr[copyIndex - midIndex] = arr[copyIndex];
            }
        }

        mergeSort(leftArr, comparator);
        mergeSort(rightArr, comparator);

        // We then merge the two subarrays.
        int i = 0;
        int j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (comparator.compare(leftArr[i], rightArr[j]) <= 0) {
                arr[i + j] = leftArr[i];
                i++;
            } else {
                arr[i + j] = rightArr[j];
                j++;
            }
        }

        // The following two loops handle "emptying" the subarrays
        // after one of them reaches its end.
        while (i < leftArr.length) {
            arr[i + j] = leftArr[i];
            i++;
        }

        while (j < rightArr.length) {
            arr[i + j] = rightArr[j];
            j++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        Queue<Integer>[] buckets = new Queue[19];

        // We must initialize the array of queues.
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<Integer>();
        }

        int k = 0;
        for (int elem : arr) {
            int elemLength = String.valueOf(Math.abs(elem)).length();
            if (elemLength > k) {
                k = elemLength;
            }
        }

        int base = 1;
        for (int iteration = 0; iteration <= k; iteration++) {
            for (int elemIndex = 0; elemIndex < arr.length; elemIndex++) {
                int currentDigit = (arr[elemIndex] / base) % 10;
                buckets[currentDigit + 9].add(Integer.valueOf(arr[elemIndex]));
            }

            int index = 0;
            for (Queue<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[index++] = (int) bucket.remove();
                }
            }
            base = base * 10;
        }
    }
}
