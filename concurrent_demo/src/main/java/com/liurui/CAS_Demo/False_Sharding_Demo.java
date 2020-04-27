package com.liurui.CAS_Demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import sun.misc.Contended;

/**
 * @author liu-rui
 * @date 2020/4/27 下午2:41
 * @description 实践伪共享
 * 参考文章：https://www.cnblogs.com/tong-yuan/p/FalseSharing.html
 * <p>
 * todo: 整理到自己的笔记中
 * <p>
 * 通过-XX:+PrintFlagsFinal观察到：
 * EnableContended  true
 * RestrictContended true
 * <p>
 * 实践：
 * 1. MyLong不添加Contended注解，执行时间：4941,2998,4655
 * 2. MyLong添加Contended注解，执行时间： 712,4676,5255
 * 3. MyLong不添加Contended注解同时增加配置-XX:-RestrictContended,执行时间：686，2324，5019
 * 4. MyLong添加Contended注解同时增加配置-XX:-RestrictContended,执行时间：734,726,624
 * 5. MyLong添加Contended注解同时增加配置-XX:-RestrictContended -XX:-EnableContended,执行时间：3460，4268，2723
 * <p>
 * 结论：
 * 1. 需要增加Contended注解，同时增加配置-XX:-RestrictContended,才有效果
 * 2. EnableContended使得注解功能生效，如果设置为false,即使标注了Contended,也是不起作用。
 * 3. 生效后的性能大概是6倍 （4941 / 734 = 6）
 * @since
 */
public class False_Sharding_Demo {
    public static void main(String[] args) throws InterruptedException {
        testPointer(new Pointer(new MyLong(0), new MyLong(0)));
        testPointer(new Pointer(new MyLong(0), new MyLong(0)));
        testPointer(new Pointer(new MyLong(0), new MyLong(0)));
    }

    private static void testPointer(Pointer pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.x.d++;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.y.d++;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(pointer);
    }

    @ToString
    @AllArgsConstructor
    static class Pointer {
        MyLong x;
        MyLong y;
    }

    @Data
    @AllArgsConstructor
    @Contended
    static class MyLong {
        volatile long d;
    }
}
