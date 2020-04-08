package com.liurui.answers.structures.array;


import com.liurui.defines.structures.array.FindMaxSumInMatrix;

/**
 * 求矩阵中最大子矩阵的和
 */
public class FindMaxSumInMatrixImpl implements FindMaxSumInMatrix {

    /**
     * 求矩阵中最大子矩阵的和
     *
     * @param matrix 矩阵
     * @return 最大子矩阵的和
     */
    @Override
    public int calc(int[][] matrix) {
        int[][] total = matrix;

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                total[i][j] += total[i - 1][j];
            }
        }
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                int[] result = new int[matrix[0].length];

                for (int k = 0; k < matrix[0].length; k++) {
                    if (i == 0) {
                        result[k] = total[j][k];
                    } else {
                        result[k] = total[j][k] - total[i - 1][k];
                    }
                }
                int currentSum = maxSubArray(result);

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }
        return maxSum;
    }

    private int maxSubArray(int[] ary) {
        int lastSum = 0;
        int maxSum = ary[0];

        for (int i = 0; i < ary.length; i++) {
            if (lastSum > 0) {
                lastSum += ary[i];
            } else {
                lastSum = ary[i];
            }

            if (lastSum > maxSum) {
                maxSum = lastSum;
            }
        }
        return maxSum;
    }
}
