package com.liurui.answers.sorts;

import com.liurui.defines.sorts.HeapSort;

/**
 * 堆排序
 */
public class HeapSortImpl implements HeapSort {
    @Override
    public int[] sort(int[] data) {
        if (data == null || data.length == 0) return data;
        int[] heap = createHeap(data);

        return sortUsingHeap(heap);
    }

    private int[] createHeap(int[] data) {
        int[] heap = new int[data.length + 1];

        for (int item : data) {
            heap[0]++;
            int i = heap[0];

            while (i > 1 && heap[i / 2] < item) {
                heap[i] = heap[i / 2];
                i /= 2;
            }
            heap[i] = item;
        }
        return heap;
    }

    private int[] sortUsingHeap(int[] heap) {
        int[] ret = new int[heap[0]];

        while (heap[0] != 0) {
            ret[heap[0] - 1] = heap[1];

            int item = heap[heap[0]];
            int parent = 1;
            int child = 2;

            heap[0]--;

            while (child <= heap[0]) {
                if ((child + 1) <= heap[0] && heap[child] < heap[child + 1]) {
                    child++;
                }

                if (item > child) {
                    break;
                } else {
                    heap[parent] = heap[child];
                    parent = child;
                    child *= 2;
                }
            }
            heap[parent] = item;
        }
        return ret;
    }
}
