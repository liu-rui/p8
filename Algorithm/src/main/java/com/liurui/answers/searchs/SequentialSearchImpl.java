package com.liurui.answers.searchs;

import com.liurui.defines.searchs.SequentialSearch;

/***
 * 顺序查找
 */
public class SequentialSearchImpl implements SequentialSearch {
    @Override
    public int find(int[] data, int item) {
        if (data == null || data.length == 0) {
            return -1;
        }
        if (data[0] == item) {
            return 0;
        }
        int first = data[0];

        data[0] = item;
        int cur = data.length - 1;

        while (data[cur] != item) {
            cur--;
        }
        data[0] = first;
        return cur == 0 ? -1 : cur;
    }
}
