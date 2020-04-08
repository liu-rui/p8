package com.liurui.answers.structures.string;

import com.liurui.defines.structures.string.PermutationUsingDictionary;

import java.util.ArrayList;

/**
 * 时间复杂度为O(NlogN)
 */
public class PermutationUsingDictionaryImpl implements PermutationUsingDictionary {
    @Override
    public String[] permutation(String str) {
        ArrayList<String> ret = new ArrayList<>();
        char[] chars = str.toCharArray();

        //排序，升序排列
        sort(chars, 0, chars.length - 1);
        int i = 0;

        while (true) {
            ret.add(new String(chars));
            System.out.println(new String(chars));
            //从右向左找小于右侧的索引i
            for (i = chars.length - 2; i >= 0 && chars[i] >= chars[i + 1]; --i) ;

            if (i < 0) break;
            int j;

            //从右向左找大于i
            for (j = chars.length - 1; j > i && chars[j] <= chars[i]; --j) ;
            //交换i,j的值
            swap(chars, i, j);
            //反转剩余的
            reverse(chars, i + 1, chars.length - 1);
        }

        return ret.toArray(new String[0]);
    }

    private void sort(char[] chars, int begin, int end) {
        if (begin >= end) return;

        int i = begin;
        int j = end;
        char item = chars[i];

        while (i < j) {
            while (i < j && chars[j] > item) {
                j--;
            }

            if (i < j) {
                chars[i++] = chars[j];
            }

            while (i < j && chars[i] <= item) {
                i++;
            }

            if (i < j) {
                chars[j--] = chars[i];
            }
        }
        chars[i] = item;
        sort(chars, begin, i - 1);
        sort(chars, i + 1, end);
    }

    private void swap(char[] str, int a, int b) {
        char tmp = str[a];

        str[a] = str[b];
        str[b] = tmp;
    }

    private void reverse(char[] str, int begin, int end) {
        int mid =  (end - begin) / 2;

        for (int i = 0; i <= mid; i++) {
            swap(str, begin + i, end - i);
        }
    }
}
