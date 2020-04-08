package com.liurui.answers.searchs;

import com.liurui.defines.searchs.BinarySearch;

/**
 * 二分查找法，需要数组事先升序排列
 */
public class BinarySearchImpl implements BinarySearch {
    @Override
    public int find(int[] data, int item) {
        if (data == null || data.length == 0) {
            return -1;
        }

        int begin = 0;
        int end = data.length - 1;
        int cur;

        while (begin <= end) {
            cur = begin + (end - begin) / 2;

            if (data[cur] == item) {
                return cur;
            } else if (item > data[cur]) {
                begin = cur + 1;
            } else {
                end = cur - 1;
            }
        }
        return -1;
    }
}
