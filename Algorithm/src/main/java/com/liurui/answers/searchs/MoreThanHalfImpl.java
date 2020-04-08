package com.liurui.answers.searchs;

import com.liurui.defines.searchs.MoreThanHalf;

/**
 * 查找数组中出现次数超过一半的数
 * 思路:
 * 1. 如果使用排序，由于次数超过一半，中间的数就是结果，但是性能受到
 * 排序的影响，时间复杂度最多也就是O(NlogN)
 * 2. 使用散列表的话，时间复杂度为O(N),但是空间复杂度为O(N)
 * <p>
 * 3. 由于次数超过一半，所以大于剩余所有数出现次数之和，这样可以定义一个变量
 * 统计次数，如果相同+1,不同-1,当为0是，重新选择一个数
 * 时间复杂度为O(N),但是空间复杂度为O(1)
 */
public class MoreThanHalfImpl implements MoreThanHalf {
    /**
     * 查找数组中出现次数超过一半的数
     *
     * @param ary 数组
     * @return 数组中出现次数超过一半的数
     */
    @Override
    public int find(int[] ary) {
        int ret = ary[0];
        int num = 1;

        for (int i = 1; i < ary.length; i++) {
            if (ret == ary[i]) {
                num++;
            } else {
                num--;
                //为0时，重新选择数
                if (num == 0) {
                    ret = ary[i];
                    num++;
                }
            }
        }
        return ret;
    }
}
