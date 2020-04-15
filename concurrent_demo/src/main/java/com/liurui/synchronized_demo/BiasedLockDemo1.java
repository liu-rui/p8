package com.liurui.synchronized_demo;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 测试偏向锁
 * 通过-XX:+PrintFlagsFinal得到偏向锁默认配置：
 * BiasedLockingBulkRebiasThreshold  20
 * BiasedLockingBulkRevokeThreshold  40
 * BiasedLockingDecayTime  25000
 * BiasedLockingStartupDelay 4000  ：默认有4秒延迟
 * <p>
 * <p>
 * <p>
 * 输出结果如下：
 * com.liurui.synchronized_demo.BiasedLockDemo1$Dog object internals:
 * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 * 0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 * 8     4        (object header)                           41 c1 00 f8 (01000001 11000001 00000000 11111000) (-134168255)
 * 12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 * <p>
 * com.liurui.synchronized_demo.BiasedLockDemo1$Dog object internals:
 * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 * 0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
 * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 * 8     4        (object header)                           41 c1 00 f8 (01000001 11000001 00000000 11111000) (-134168255)
 * 12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 * <p>
 * com.liurui.synchronized_demo.BiasedLockDemo1$Dog object internals:
 * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 * 0     4        (object header)                           05 c8 00 fc (00000101 11001000 00000000 11111100) (-67057659)
 * 4     4        (object header)                           ad 7f 00 00 (10101101 01111111 00000000 00000000) (32685)
 * 8     4        (object header)                           41 c1 00 f8 (01000001 11000001 00000000 11111000) (-134168255)
 * 12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 * <p>
 * com.liurui.synchronized_demo.BiasedLockDemo1$Dog object internals:
 * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 * 0     4        (object header)                           05 c8 00 fc (00000101 11001000 00000000 11111100) (-67057659)
 * 4     4        (object header)                           ad 7f 00 00 (10101101 01111111 00000000 00000000) (32685)
 * 8     4        (object header)                           41 c1 00 f8 (01000001 11000001 00000000 11111000) (-134168255)
 * 12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 * <p>
 * 325333723
 * com.liurui.synchronized_demo.BiasedLockDemo1$Dog object internals:
 * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 * 0     4        (object header)                           01 db 32 64 (00000001 11011011 00110010 01100100) (1681054465)
 * 4     4        (object header)                           13 00 00 00 (00010011 00000000 00000000 00000000) (19)
 * 8     4        (object header)                           41 c1 00 f8 (01000001 11000001 00000000 11111000) (-134168255)
 * 12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 * <p>
 * 结论：
 * 1. 偏向锁启动默认有4秒延迟，启动前mark word 后三位是001,4秒后新创建的对象才是偏向锁(mark word后3位：010)
 * 2. 启动后，标记只是启用了偏向锁，只有使用了synchronized关键字后才会存储线程ID,偏向特定的线程
 * 3. 启动后，synchronized代码块执行后，线程ID仍然保留，偏向与此线程
 * 4. 调用hashcode后，是有偏向锁转变为无锁状态，存储mark word存储hashcode值
 * 4. 对象的hashcode方法返回是int型，4个字节，但是对象头中的hashcode是31位的没有空间存下，当超过范围后会存储System.identityHashCode方法返回的值
 * 5. 对象头中的hashcode值由System.identityHashCode方法最终决定，对象的hashcode只是提供了基数而已。
 */
public class BiasedLockDemo1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(ClassLayout.parseInstance(new Dog()).toPrintable());
        TimeUnit.SECONDS.sleep(5);
        Dog dog = new Dog();
        System.out.println(ClassLayout.parseInstance(dog).toPrintable());

        synchronized (dog) {
            System.out.println(ClassLayout.parseInstance(dog).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(dog).toPrintable());
        System.out.println(dog.hashCode()); //调用了hashcode
        System.out.println(System.identityHashCode(dog));
        System.out.println(ClassLayout.parseInstance(dog).toPrintable());
    }

    public static class Dog {
        @Override
        public int hashCode() {
            return 20;
        }
    }
}
