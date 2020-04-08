package com.liurui.answers.searchs;

import com.liurui.defines.searchs.FindK;

/**
 * 找出数组中第k大的数
 * 时间复杂度为O(N)
 * 思想：
 * 1. 利用快速排序的思想，每次partition后的位置就是第k大的数（必要条件是升序排列）
 * 2. 比较partition后的位置和k的大小
 */
public class FindKImpl implements FindK {

    /**
     * 找出数组中第k大的数
     *
     * @param ary 数组
     * @param k   第k
     * @return 第k大的数
     */
    @Override
    public int find(int[] ary, int k) {
        return find(ary, k, 0, ary.length - 1);
    }

    private int find(int[] ary, int k, int begin, int end) {
        int i = partition(ary, begin, end);

        if (i + 1 > k) {
            return find(ary, k, begin, i - 1);
        } else if (i + 1 < k) {
            return find(ary, k, i + 1, end);
        } else {
            return ary[i];
        }
    }

    private int partition(int[] ary, int begin, int end) {
        if (begin < end) {
            int key = ary[begin];

            while (begin < end) {
                while (begin < end && ary[end] < key) {
                    end--;
                }

                if (begin < end) {
                    ary[begin++] = ary[end];
                }

                while (begin < end && ary[begin] >= key) {
                    begin++;
                }

                if (begin < end) {
                    ary[end--] = ary[begin];
                }
            }
            ary[begin] = key;
        }
        return begin;
    }
}
