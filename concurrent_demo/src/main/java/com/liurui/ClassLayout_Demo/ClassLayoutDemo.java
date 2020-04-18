package com.liurui.ClassLayout_Demo;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author liu-rui
 * @date 2020/4/14 下午2:41
 * @description 测试对象在内存中布局，使用jol-core(Java Object Layout)
 * @since
 */
public class ClassLayoutDemo {
    public static class Customer {
        private String name;
        private int age;

        public Customer(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        System.out.println("打印整型内存布局：");
        System.out.println(ClassLayout.parseInstance(1).toPrintable());
        System.out.println(ClassLayout.parseInstance(2).toPrintable());
        System.out.println("---------------\n\n");

        //java.lang.Integer object internals:
        // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4        (object header)                           de 21 00 f8 (11011110 00100001 00000000 11111000) (-134209058)
        //     12     4    int Integer.value                             1
        //Instance size: 16 bytes
        //Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        //
        //java.lang.Integer object internals:
        // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4        (object header)                           de 21 00 f8 (11011110 00100001 00000000 11111000) (-134209058)
        //     12     4    int Integer.value                             2
        //Instance size: 16 bytes
        //Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        //结论：
        //klass word相同，指向同一个Integer.class
        //整型占用的内存大小为16字节=Mark word（8） + klass Word（4） + 内容（4）


        System.out.println("打印布尔内存布局：");
        System.out.println(ClassLayout.parseInstance(true).toPrintable());
        System.out.println(ClassLayout.parseInstance(new Boolean(false)).toPrintable());
        System.out.println("---------------\n\n");

        //java.lang.Boolean object internals:
        // OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
        //      0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4           (object header)                           0a 20 00 f8 (00001010 00100000 00000000 11111000) (-134209526)
        //     12     1   boolean Boolean.value                             true
        //     13     3           (loss due to the next object alignment)
        //Instance size: 16 bytes
        //Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
        //
        //java.lang.Boolean object internals:
        // OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
        //      0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4           (object header)                           0a 20 00 f8 (00001010 00100000 00000000 11111000) (-134209526)
        //     12     1   boolean Boolean.value                             false
        //     13     3           (loss due to the next object alignment)
        //Instance size: 16 bytes
        //Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
        //结论：
        //1. 基础类型和包装类大小是相同的
        //2. 大小是16字节=mark word(8) + klass word(4) + 内容(1) + 对齐(3)


        System.out.println("打印数组内存布局：");
        System.out.println(ClassLayout.parseInstance(new int[100000]).toPrintable());
        System.out.println(ClassLayout.parseInstance(new int[]{1,2,3,4}).toPrintable());
        System.out.println(ClassLayout.parseInstance(new Integer[2]).toPrintable());
        System.out.println(ClassLayout.parseInstance(new Integer[]{6,7,8,9,10}).toPrintable());
        System.out.println("---------------\n\n");
        //[I object internals:
        // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4        (object header)                           61 01 00 f8 (01100001 00000001 00000000 11111000) (-134217375)
        //     12     4        (object header)                           a0 86 01 00 (10100000 10000110 00000001 00000000) (100000)
        //     16 400000    int [I.<elements>                             N/A
        //Instance size: 400016 bytes
        //Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        //
        //[I object internals:
        // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4        (object header)                           61 01 00 f8 (01100001 00000001 00000000 11111000) (-134217375)
        //     12     4        (object header)                           04 00 00 00 (00000100 00000000 00000000 00000000) (4)
        //     16    16    int [I.<elements>                             N/A
        //Instance size: 32 bytes
        //Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        //
        //[Ljava.lang.Integer; object internals:
        // OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
        //      0     4                     (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4                     (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4                     (object header)                           79 59 00 f8 (01111001 01011001 00000000 11111000) (-134194823)
        //     12     4                     (object header)                           02 00 00 00 (00000010 00000000 00000000 00000000) (2)
        //     16     8   java.lang.Integer Integer;.<elements>                       N/A
        //Instance size: 24 bytes
        //Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        //
        //[Ljava.lang.Integer; object internals:
        // OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
        //      0     4                     (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4                     (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4                     (object header)                           79 59 00 f8 (01111001 01011001 00000000 11111000) (-134194823)
        //     12     4                     (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
        //     16    20   java.lang.Integer Integer;.<elements>                       N/A
        //     36     4                     (loss due to the next object alignment)
        //Instance size: 40 bytes
        //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total




        System.out.println("打印整型包装类数组内存布局：");
        System.out.println(ClassLayout.parseInstance(new Integer[10]).toPrintable());
        System.out.println("---------------\n\n");


        System.out.println("打印字符串hello内存布局：");
        System.out.println(ClassLayout.parseInstance("hello").toPrintable());
        System.out.println(ClassLayout.parseInstance("word").toPrintable());
        System.out.println("---------------\n\n");

        //java.lang.String object internals:
        // OFFSET  SIZE     TYPE DESCRIPTION                               VALUE
        //      0     4          (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4          (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4          (object header)                           c2 02 00 f8 (11000010 00000010 00000000 11111000) (-134217022)
        //     12     4   char[] String.value                              [h, e, l, l, o]
        //     16     4      int String.hash                               0
        //     20     4          (loss due to the next object alignment)
        //Instance size: 24 bytes
        //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        //
        //java.lang.String object internals:
        // OFFSET  SIZE     TYPE DESCRIPTION                               VALUE
        //      0     4          (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4          (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4          (object header)                           c2 02 00 f8 (11000010 00000010 00000000 11111000) (-134217022)
        //     12     4   char[] String.value                              [w, o, r, d]
        //     16     4      int String.hash                               0
        //     20     4          (loss due to the next object alignment)
        //Instance size: 24 bytes
        //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total





        System.out.println("打印Customer内存布局：");
        System.out.println(ClassLayout.parseInstance(new Customer("ren", 56)).toPrintable());
        System.out.println("---------------\n\n");
    }
}
