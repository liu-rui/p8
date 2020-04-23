package com.liurui.ClassLayout_Demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * 压缩指针标记能压缩以下三种：
 * 1. 每个class的属性指针
 * 2. 每个对象的属性指针
 * 3. 普通对象数组的每个元素指针
 * 验证以上三种情况
 *
 *
 *
 */
@Slf4j
public class UseCompressedOopsDemo2 {
    @Getter
    @AllArgsConstructor
    static class Customer {
        private int id;
        private Demo1.Order order;
    }

    @Getter
    @AllArgsConstructor
    static class Order {
        private String orderId;
        private double price;
    }

    public static void main(String[] args) {
        log.info(ClassLayout.parseInstance(new Demo1.Customer(1, new Demo1.Order("100", 34))).toPrintable());

        //15:43:52.157 [main] INFO com.liurui.ClassLayout_Demo.UseCompressedOopsDemo2 - com.liurui.ClassLayout_Demo.Demo1$Customer object internals:
        // OFFSET  SIZE                                      TYPE DESCRIPTION                               VALUE
        //      0     4                                           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4                                           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4                                           (object header)                           22 db 00 f8 (00100010 11011011 00000000 11111000) (-134161630)
        //     12     4                                       int Customer.id                               1
        //     16     4   com.liurui.ClassLayout_Demo.Demo1.Order Customer.order                            (object)
        //     20     4                                           (loss due to the next object alignment)
        //Instance size: 24 bytes
        //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        // ---关闭压缩指针后的输出：-----------
        //15:46:27.854 [main] INFO com.liurui.ClassLayout_Demo.UseCompressedOopsDemo2 - com.liurui.ClassLayout_Demo.Demo1$Customer object internals:
        // OFFSET  SIZE                                      TYPE DESCRIPTION                               VALUE
        //      0     4                                           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4                                           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4                                           (object header)                           48 58 4e 87 (01001000 01011000 01001110 10000111) (-2024908728)
        //     12     4                                           (object header)                           a6 7f 00 00 (10100110 01111111 00000000 00000000) (32678)
        //     16     4                                       int Customer.id                               1
        //     20     4                                           (alignment/padding gap)
        //     24     8   com.liurui.ClassLayout_Demo.Demo1.Order Customer.order                            (object)
        //Instance size: 32 bytes
        //Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
        //分析：
        //1. klass word，由4byte ->  8 byte
        //2.  Customer对象的Order属性指针，由4byte ->  8 byte



        log.info("---------------");
        log.info(ClassLayout.parseInstance(new Order[]{ new Order("1" , 1) ,new Order("2" , 2) ,new Order("3" , 3) } ).toPrintable());

        //16:12:22.008 [main] INFO com.liurui.ClassLayout_Demo.UseCompressedOopsDemo2 - [Lcom.liurui.ClassLayout_Demo.UseCompressedOopsDemo2$Order; object internals:
        // OFFSET  SIZE                                                       TYPE DESCRIPTION                                VALUE
        //      0     4                                                            (object header)                            01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4                                                            (object header)                            00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4                                                            (object header)                            d5 1d 02 f8 (11010101 00011101 00000010 11111000) (-134079019)
        //     12     4                                                            (object header)                            03 00 00 00 (00000011 00000000 00000000 00000000) (3)
        //     16    12   com.liurui.ClassLayout_Demo.UseCompressedOopsDemo2$Order UseCompressedOopsDemo2$Order;.<elements>   N/A
        //     28     4                                                            (loss due to the next object alignment)
        //Instance size: 32 bytes
        //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        // ---关闭压缩指针后的输出：-----------
        //16:11:44.618 [main] INFO com.liurui.ClassLayout_Demo.UseCompressedOopsDemo2 - [Lcom.liurui.ClassLayout_Demo.UseCompressedOopsDemo2$Order; object internals:
        // OFFSET  SIZE                                                       TYPE DESCRIPTION                                VALUE
        //      0     4                                                            (object header)                            01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4                                                            (object header)                            00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4                                                            (object header)                            78 aa 79 bf (01111000 10101010 01111001 10111111) (-1082545544)
        //     12     4                                                            (object header)                            2d 7f 00 00 (00101101 01111111 00000000 00000000) (32557)
        //     16     4                                                            (object header)                            03 00 00 00 (00000011 00000000 00000000 00000000) (3)
        //     20     4                                                            (alignment/padding gap)
        //     24    24   com.liurui.ClassLayout_Demo.UseCompressedOopsDemo2$Order UseCompressedOopsDemo2$Order;.<elements>   N/A
        //Instance size: 48 bytes
        //Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
        //分析：
        //1. 对象数组组成部分： mark word , klass word , 数组长度，数组元素和对齐组成
        //2. 数组长度始终占用4byte,即int类型
        //3. 共有三个元素；  元素由4byte(12/4) ->  8 byte(24/3)

    }

}
