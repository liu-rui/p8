package com.liurui.answers.questions;

/**
 * 字符大小写转换
 *
 * 0x20 的反码是0x5f
 */
public class LowerSuperCase {

    public static void main(String[] args) {
        System.out.println(Integer.toString('A' , 2));
        System.out.println( '0' +  Integer.toString(0x20, 2));
        System.out.println(Integer.toString('a', 2));
        System.out.println("\n");
        //大写转换为小写
        assert 'a' == (char) ('A' ^ 0x20);
        //小写转换为大写
        assert 'B' == (char) ('a' & 0x5f);
        System.out.println(Integer.toString('a', 2));
        System.out.println( Integer.toString(0x5f, 2));
        System.out.println(Integer.toString('A' , 2));
    }
}
