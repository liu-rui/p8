package com.liurui.answers.sorts;

import com.liurui.defines.sorts.InsertSort;

public class InsertSortImpl implements InsertSort {
    @Override
    public int[] sort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int tmp = data[i];
            int j = i;

            for (; j > 0 && data[j - 1] > tmp; j--) {
                data[j] = data[j - 1];
            }
            data[j] = tmp;
        }
        return data;
    }
}
