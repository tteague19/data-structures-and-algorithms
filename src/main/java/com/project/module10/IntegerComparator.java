package com.project.module10;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer> {
    
    @Override
    public int compare(Integer firstInteger, Integer secondInteger) {
        return Integer.compare(firstInteger, secondInteger);
    }
}
