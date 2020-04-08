package com.liurui.answers.sorts;

import com.liurui.defines.sorts.MergeSort;

/**
 * 归并排序
 */
public class MergeSortImpl implements MergeSort {
    private int[] tmp;

    @Override
    public int[] sort(int[] data) {
        tmp = new int[data.length];
        sort(data, 0, data.length - 1);
        return data;
    }

    private void sort(int[] data, int begin, int end) {
        if (begin >= end) return;

        int mid = begin + (end - begin) / 2;
        sort(data, begin, mid);
        sort(data, mid + 1, end);
        merge(data, begin, mid, end);
    }

    private void merge(int[] data, int begin, int mid, int end) {
        int i = begin;
        int left = begin;
        int right = mid + 1;

        while (left <= mid && right <= end) {
            if (data[left] <= data[right]) {
                tmp[i++] = data[left++];
            } else {
                tmp[i++] = data[right++];
            }
        }

        while (left <= mid) {
            tmp[i++] = data[left++];
        }

        while (right <= end) {
            tmp[i++] = data[right++];
        }

        i = begin;

        while (i <= end) {
            data[i] = tmp[i];
            i++;
        }
    }
}
