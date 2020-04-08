package com.liurui.answers.questions;

import com.liurui.defines.questions.TopN;

/**
 * 求数组中最大的前num个数
 * 时间复杂度为O((N-M)*logM)
 * N为数组的长度，M为结果的个数
 */
public class TopNImpl implements TopN {
    /**
     * 求数组中最大的前num个数
     *
     * @param data 数组
     * @param num  需要返回的数量
     * @return 最大数列表，结果从大到小排列
     */
    @Override
    public int[] max(int[] data, int num) {
        int[] heap = new int[num + 1];

        for (int item : data) {
            addForMax(heap, item);
        }
        return sortForMax(heap);
    }

    private void addForMax(int[] heap, int data) {
        //没有满
        if (heap[0] != heap.length - 1) {
            heap[0]++;
            int i = heap[0];

            while (i > 1 && heap[i / 2] > data) {
                heap[i] = heap[i / 2];
                i /= 2;
            }
            heap[i] = data;
        } else if (heap[1] < data) {
            //目前已经满了，但是发现有新的比较大的数据
            int parent = 1;
            int child = 2;

            while (child <= heap[0]) {
                if (child + 1 <= heap[0] && heap[child] > heap[child + 1]) {
                    child++;
                }
                if (data < heap[child]) {
                    break;
                } else {
                    heap[parent] = heap[child];
                    parent = child;
                    child *= 2;
                }
            }
            heap[parent] = data;
        }
    }

    private int[] sortForMax(int[] heap) {
        int[] data = new int[heap[0]];

        while (heap[0] != 0) {
            data[heap[0] - 1] = heap[1];

            int item = heap[heap[0]];
            int parent = 1;
            int child = 2;

            heap[0]--;

            while (child <= heap[0]) {
                if (child + 1 <= heap[0] && heap[child] > heap[child + 1]) {
                    child++;
                }

                if (item < heap[child]) {
                    break;
                } else {
                    heap[parent] = heap[child];
                    parent = child;
                    child *= 2;
                }
            }
            heap[parent] = item;
        }
        return data;
    }

    /**
     * 求数组中最小的前num个数
     *
     * @param data 数组
     * @param num  需要返回的数量
     * @return 最小数列表，结果从小到大排列
     */
    @Override
    public int[] min(int[] data, int num) {
        int[] bigHeap = new int[num + 1];

        for (int item : data) {
            addForMin(bigHeap, item);
        }
        return sortForMin(bigHeap);
    }

    private void addForMin(int[] bigHeap, int data) {
        if (bigHeap[0] != bigHeap.length - 1) {
            //堆还没有满，需要加入堆中
            bigHeap[0]++;
            int i = bigHeap[0];

            while (i > 1 && bigHeap[i / 2] < data) {
                bigHeap[i] = bigHeap[i / 2];
                i /= 2;
            }
            bigHeap[i] = data;
        } else if (bigHeap[1] > data) {
            //虽然堆已经满了，但是发现了小的数，需要加入堆中
            int parent = 1;
            int child = 2;

            while (child <= bigHeap[0]) {
                if (child + 1 <= bigHeap[0] && bigHeap[child] < bigHeap[child + 1]) {
                    child++;
                }
                if (data > bigHeap[child]) {
                    break;
                } else {
                    bigHeap[parent] = bigHeap[child];
                    parent = child;
                    child *= 2;
                }
            }
            bigHeap[parent] = data;
        }
    }

    private int[] sortForMin(int[] bigHeap) {
        int[] ret = new int[bigHeap[0]];

        while (bigHeap[0] != 0) {
            ret[bigHeap[0] - 1] = bigHeap[1];

            int data = bigHeap[bigHeap[0]];
            int parent = 1;
            int child = 2;

            bigHeap[0]--;

            if (bigHeap[0] == 0) {
                break;
            } else {
                while (child <= bigHeap[0]) {
                    if (child + 1 <= bigHeap[0] && bigHeap[child] < bigHeap[child + 1]) {
                        child++;
                    }
                    if (data > bigHeap[child]) {
                        break;
                    } else {
                        bigHeap[parent] = bigHeap[child];
                        parent = child;
                        child *= 2;
                    }
                }
                bigHeap[parent] = data;
            }
        }
        return ret;
    }
}