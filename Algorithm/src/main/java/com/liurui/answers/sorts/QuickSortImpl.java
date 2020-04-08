package com.liurui.answers.sorts;

import com.liurui.defines.sorts.QuickSort;

/***
 * 快速排序
 */
public class QuickSortImpl implements QuickSort {

    @Override
    public int[] sort(int[] data) {
        sort(data, 0, data.length - 1);
        return data;
    }

    private void sort(int[] data, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int i = begin;
        int j = end;
        int item = data[begin];

        while (i < j) {
            while (i < j && data[j] >= item) {
                j--;
            }

            if (i < j) {
                data[i++] = data[j];
            }

            while (i < j && data[i] < item) {
                i++;
            }

            if (i < j) {
                data[j--] = data[i];
            }
        }
        data[i] = item;
        sort(data, begin, i - 1);
        sort(data, i + 1, end);
    }
}
