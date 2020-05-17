package com.liurui.bit_operation_demo;

import javax.sound.midi.Soundbank;

/**
 * @author liu-rui
 * @date 2020/4/28 下午6:02
 * @description
 * 位运算
 * >>有符号右移： 右移之后， 左边的补上符号位， 正数补0， 负数补1。
 * >>>无符号右移： 右移之后， 无论该数是正数还是负数， 右移之后左边都是补上0。
 * <<左移不区分有符号和无符号， 都是左移之后右边补上0， 最左边的符号位也直接移走。
 * @since
 */
public class Demo1 {
    public static void main(String[] args) {
        String separator = "\n\n#############################################################\n" +
                "\t %s \n" +
                "#############################################################";


        System.out.println(String.format(separator, "有符号右移 >>: 右移之后，左边的补上符号位，正数补0，负数补1"));
        System.out.println("20二进制: " + Integer.toBinaryString(20));
        System.out.println("20有符号右移2位: "+ Integer.toBinaryString(20 >> 2));

        System.out.println();

        System.out.println("-20二进制: " + Integer.toBinaryString(-20));
        System.out.println("-20有符号右移2位: " + Integer.toBinaryString(-20 >> 2));


        System.out.println(String.format(separator, "无符号右移 >>>: 右移之后，无论该数是正数还是负数，右移之后左边都是补上0"));
        System.out.println("20二进制: " + Integer.toBinaryString(20));
        System.out.println("20无符号右移2位: "+ Integer.toBinaryString(20 >>> 2));

        System.out.println();

        System.out.println("-20二进制: " + Integer.toBinaryString(-20));
        System.out.println("-20无符号右移2位: " + Integer.toBinaryString(-20 >>> 2));


        System.out.println(String.format(separator, "左移 <<: 左移不区分有符号和无符号，都是左移之后右边补上0，最左边的符号位也直接移走"));
        System.out.println("20二进制: " + Integer.toBinaryString(20));
        System.out.println("20左移2位: "+ Integer.toBinaryString(20 << 2));

        System.out.println();

        System.out.println("-20二进制: " + Integer.toBinaryString(-20));
        System.out.println("-20左移2位: " + Integer.toBinaryString(-20 << 2));

        System.out.println("\n\n\n");

        // 十进制转二进制 | 12 -> 1100
        System.out.println(Integer.toBinaryString(12));
        // 二进制转十进制 | 1100 -> 12
        System.out.println(Integer.parseInt("1100", 2));

        // 十六进制转十进制 | FF -> 255
        System.out.println(Integer.parseInt("FF", 16));
        // 十六进制转十进制 | FF -> 255
        System.out.println(Integer.valueOf("FF",16));   // 底层也是调用了parseInt
        // 十进制转十六进制 | 255 -> ff
        System.out.println(Integer.toHexString(255));


        System.out.println("\n\n");

        /**
         *  或运算
         *      01100
         *  |   10111
         * -----------
         *      11111 = 31
         * */
        System.out.println(12 | 23);

        /**
         *          1100
         * 左移2位  110000 = 48
         * */
        System.out.println(12 << 2);

        /**
         *          1100
         * 右移2位   11 = 3
         * */
        System.out.println(12 >> 2);

        /**
         *  与运算
         *      01100
         *  &   10111
         * -----------
         *      00100 = 4
         * */
        System.out.println(12 & 23);

        /**
         * 按位取反
         *
         * 计算过程
         * 12的源码:       00001100
         * 对12的原码取反:  11110011 (结果即为 -13 的补码[计算机中带符号的整数都是采用二进制的补码进行存储])
         * 转反码:         11110010 (补码减1)
         * 转原码:         10001101 (符号位不变, 其他位取反) = -13
         *
         * ~x = -x - 1
         * ~12 = -12 - 1 = -13
         * */
        System.out.println(~12);


        /**
         * 异或
         * 0 ^ 1 = 1
         * 1 ^ 1 = 0
         * 0 ^ 0 = 0
         * 1 ^ 0 = 1
         * */
        System.out.println(2 ^ 8);


    }
}
