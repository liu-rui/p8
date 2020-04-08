package com.liurui.answers.sorts;

import com.liurui.defines.sorts.ShellSort;

public class ShellSortImpl implements ShellSort {
    @Override
    public int[] sort(int[] data) {
        for (int i = data.length / 2; i > 0; i /= 2) {
            for (int j = i; j < data.length; j++) {
                for (int k = j; k - i >= 0 && data[k] < data[k - i]; k -= i) {
                    int tmp = data[k];
                    data[k] = data[k - i];
                    data[k - i] = tmp;
                }
            }
        }

        return data;
    }
}
