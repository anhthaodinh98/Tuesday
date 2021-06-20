package com.testarchitect.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Common {
    /**
     * This function is to check if the input list is sorted or not
     * @param list: List<String>
     * @return
     */
    public static boolean isListStringSorted(List<String> list) {
        if (list.isEmpty() || list.size() == 1) {
            return true;
        }
        List<String> lstOriginal = new ArrayList<>(list);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        return list.equals(lstOriginal);
    }
}
