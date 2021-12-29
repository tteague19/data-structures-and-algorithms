package com.project.module11;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer firstInteger, Integer secondInteger) {
        return Integer.compare(firstInteger, secondInteger);
    }
}
