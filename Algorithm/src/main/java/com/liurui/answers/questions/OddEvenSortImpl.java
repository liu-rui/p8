package com.liurui.answers.questions;

import com.liurui.defines.questions.OddEvenSort;

/***
 * 整理一个数组，将奇数放左边，偶数放右边
 * O(LogN)
 */
public class OddEvenSortImpl implements OddEvenSort {

    @Override
    public int[] exec(int[] data) {
        int i = 0;
        int j = data.length - 1;

        while (i < j) {
            while (i != j && data[i] % 2 == 1) {
                i++;
            }

            while (i != j && data[j] % 2 == 0) {
                j--;
            }

            if (i != j) {
                int tmp = data[i];
                data[i] = data[j];
                data[j] = tmp;

                i++;
                j--;
            }
        }
        return data;
    }
}
