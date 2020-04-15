package com.liurui;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author liu-rui
 * @date 2020/4/14 下午2:22
 * @description 测试指针压缩配置的效果, UseCompressedOops
 *
 *
 *
 * 64默认是开启的，输出如下：
 * java.lang.Object object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           d5 01 00 f8 (11010101 00000001 00000000 11111000) (-134217259)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * 12
 * 16
 *
 * 关闭-XX:-UseCompressedOops,输出如下：
 *java.lang.Object object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           80 4b 3c bc (10000000 01001011 00111100 10111100) (-1136899200)
 *      12     4        (object header)                           9b 7f 00 00 (10011011 01111111 00000000 00000000) (32667)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
 *
 * 16
 * 16
 *
 * 设置最大堆大小为40g,显示开启压缩标记：-Xmx40g -XX:+UseCompressedOops
 * OpenJDK 64-Bit Server VM warning: Max heap size too large for Compressed Oops
 * java.lang.Object object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           80 db 9a 44 (10000000 11011011 10011010 01000100) (1150999424)
 *      12     4        (object header)                           26 7f 00 00 (00100110 01111111 00000000 00000000) (32550)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
 *
 * 16
 * 16
 *
 *
 * 结论：
 * 1. 64位下是默认开启的，通过参数-XX:+PrintFlagsFinal确认结论
 * 2. mark word占用8个字节，参数是否开启都是相同的
 * 3. klass word,参数开启时（默认）占用了4位，参数关闭后是8字节
 * 4. 启动后，header长度是12字节，非8整数倍，通过对齐方式（4个字节）达到8的整数倍。
 * 4. 当堆大小超过32G时，压缩标记失效，采用8字节。
 * @since
 */
public class UseCompressedOopsDemo {
    public static void main(String[] args) {
        final ClassLayout classLayout = ClassLayout.parseInstance(new Object());
        System.out.println(classLayout.toPrintable());
        System.out.println(classLayout.headerSize());
        System.out.println(classLayout.instanceSize());
    }
}
