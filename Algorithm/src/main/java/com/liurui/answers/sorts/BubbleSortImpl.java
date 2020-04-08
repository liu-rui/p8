package com.liurui.answers.sorts;

import com.liurui.defines.sorts.BubbleSort;

public class BubbleSortImpl implements BubbleSort {
    @Override
    public int[] sort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int max = 0;
            for (int j = 1; j <= data.length - i; j++) {
                if (data[j] > data[max]) {
                    max = j;
                }
            }

            if (max != data.length - i) {
                int tmp = data[max];

                data[max] = data[data.length - i];
                data[data.length - i] = tmp;
            }
        }
        return data;
    }
}
