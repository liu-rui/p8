package com.liurui.synchronized_demo;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author liu-rui
 * @date 2020/4/15 下午12:19
 * @description 轻量级锁
 * <p>
 * 测试：关闭偏向锁后，多线程下，无竟态状态时，使用的是轻量级锁，头标记为00
 * <p>
 * 首先关闭偏向锁： -XX:-UseBiasedLocking
 *
 * [main] INFO LockDemo1 - com.liurui.synchronized_demo.LockDemo1$MyLock object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           c0 c7 00 f8 (11000000 11000111 00000000 11111000) (-134166592)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * [t1] INFO LockDemo1 - com.liurui.synchronized_demo.LockDemo1$MyLock object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           e0 88 a2 55 (11100000 10001000 10100010 01010101) (1436715232)
 *       4     4        (object header)                           28 7f 00 00 (00101000 01111111 00000000 00000000) (32552)
 *       8     4        (object header)                           c0 c7 00 f8 (11000000 11000111 00000000 11111000) (-134166592)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * [t1] INFO LockDemo1 - com.liurui.synchronized_demo.LockDemo1$MyLock object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           c0 c7 00 f8 (11000000 11000111 00000000 11111000) (-134166592)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * [t2] INFO LockDemo1 - com.liurui.synchronized_demo.LockDemo1$MyLock object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           c8 78 92 55 (11001000 01111000 10010010 01010101) (1435662536)
 *       4     4        (object header)                           28 7f 00 00 (00101000 01111111 00000000 00000000) (32552)
 *       8     4        (object header)                           c0 c7 00 f8 (11000000 11000111 00000000 11111000) (-134166592)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * [t2] INFO LockDemo1 - com.liurui.synchronized_demo.LockDemo1$MyLock object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           c0 c7 00 f8 (11000000 11000111 00000000 11111000) (-134166592)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 *
 * 结论：
 *1. 为了便于测试轻量级锁，关闭了偏向锁功能，故对象创建后头部state标记为未使用(01)
 *2. 当在同步块中时，使用了轻量级锁（01），并记录了lock record
 *
 * @since
 */
@Slf4j(topic = "LockDemo1")
public class LockDemo1 {
    public static void main(String[] args) {
        MyLock lock = new MyLock();

        log.info(ClassLayout.parseInstance(lock).toPrintable());

        Thread t1 = new Thread(() -> {
            run(lock);
            run(lock);

            synchronized (LockDemo1.class) {
                LockDemo1.class.notifyAll();
            }
        }, "t1");

        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (LockDemo1.class) {
                try {
                    LockDemo1.class.wait();
                } catch (InterruptedException e) {
                }
            }
            run(lock);
        }, "t2");

        t2.start();
    }

    static void run(MyLock lock) {
        synchronized (lock) {
            log.info(ClassLayout.parseInstance(lock).toPrintable());
        }
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }


    static class MyLock {

    }
}
