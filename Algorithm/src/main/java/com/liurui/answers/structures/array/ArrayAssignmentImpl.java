package com.liurui.answers.structures.array;


import com.liurui.defines.structures.array.ArrayAssignment;

/**
 * 数组赋值问题
 * 现在有两个数组,分别为a[]和b[],其中a[]是已知的，求b[].
 * 规则是b[i]=(a[0]*a[1]*..*a[n-1])/a[i],已知结果不会溢出，还有几个要求：
 * 1.不准用除法运算
 * 2.除了循环计数值，a[N],b[N]外，不准再用其他任何变量（包括局部变量，全局变量等）
 * 3.满足时间复杂度O（n），空间复杂度O（1）
 *
 *
 * 解题思路：
 *  1. b[i]= a[0]*..*a[i-1]*a[i+1]*..a[n-1] ,这样就解决了第一个问题，不用除法
 *  2. 使用b[0]作为局部变量
 *  3. 求b[j]时，分两段求
 */
public class ArrayAssignmentImpl implements ArrayAssignment {

    /**
     * 数组赋值问题
     *
     * @param a 已知的数组a
     * @return 未知的数组b
     */
    @Override
    public int[] get(int[] a) {
        int[] b = new int[a.length];

        b[0] = 1;

        //求前半段
        for (int i = 1; i < a.length; i++) {
            b[0] *= a[i - 1];
            b[i] = b[0];
        }

        b[0] = 1;

        //求后半段
        for (int i = a.length - 2; i > 0; i--) {
            b[0] *= a[i + 1];
            b[i] *= b[0];
        }
        //此时的b[0]为a[2]*..a[n-1],就差一个a[1]
        b[0] *= a[1];
        return b;
    }
}
