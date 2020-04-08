package com.liurui.answers.structures.array;


import com.liurui.defines.structures.array.FindMaxSumSubArrayUsingExhaustive;

import java.util.Arrays;

/**
 * 找出连加值最大的子数组，使用穷举法
 * 子数组：数组中连续的一部分
 * 时间复杂度为O(N^3)
 */
public class FindMaxSumSubArrayUsingExhaustiveImpl implements FindMaxSumSubArrayUsingExhaustive {


    /**
     * 找出连加值最大的子数组
     *
     * @param ary 原数组
     * @return 子数组
     */
    @Override
    public int[] find(int[] ary){
        int maxSum=ary[0];
        int begin = 0;
        int end = 0;

        for(int i  = 0;i< ary.length;i++){
            for(int j = i;j< ary.length;j++){
                int sum = 0;

                for(int k = i;k<=j;k++){
                    sum+= ary[k];
                }

                if(sum > maxSum){
                    begin = i;
                    end = j;
                    maxSum = sum;
                }
            }
        }

        return Arrays.copyOfRange(ary , begin , end+1);
    }
}
