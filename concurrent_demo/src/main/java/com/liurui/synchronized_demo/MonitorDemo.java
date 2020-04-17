package com.liurui.synchronized_demo;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/4/17 下午4:35
 * @description
 * @since 测试下使用synchronized产生重量级锁时的对象头mark word信息
 *
 *
 * 输出结果：
 * 1. [t1] 同步前         00000001
 * 2. [t1] 同步中-休息前   01001000
 * 3. [t2] 同步前         11001010
 * 4. [t1] 同步中-休息后   11001010
 * 5. [t2]  同步块中      11001010
 * 6. [t1] 同步块后       11001010
 * 7. [t2] 同步块后       11001010
 * 8. [t3] 同步前         00000001
 * 9. [t3] 同步块中       01010000
 * 10.[t3] 同步块后       00000001
 * 分析：
 * 1. 由于偏向锁启动有延迟，还未生效，所以状态为无锁状态（001）
 * 2. 不存在竞态，所以使用轻量级锁（00）
 * 3. 其他线程访问了锁对象，存在竞态，由轻量级锁升级到重量级监视器(10)
 * 4. T1休息结束，状态为重量级监视器(10)
 * 5. T2从wait队列唤醒，所以状态还是重量级监视器(10)
 * 6. 此时T2没有结束，状态还是重量级监视器(10)
 * 7. T2线程没有结束，状态还是重量级监视器(10)
 * 8. 无锁状态(001)
 * 9. 使用轻量级锁(00)
 * 10. 无锁状态(001)
 *
 * 总结：
 * 1. 无静态模式的时候使用轻量级锁
 * 2. 一旦有竞态时，会升级到重量级锁
 *
 */
@Slf4j(topic = "MonitorDemo")
public class MonitorDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.info("同步前 {}", ClassLayout.parseInstance(MonitorDemo.class).toPrintable());

            synchronized (MonitorDemo.class) {
                log.info("同步中-休息前 {}", ClassLayout.parseInstance(MonitorDemo.class).toPrintable());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                }
                log.info("同步中-休息后 {}", ClassLayout.parseInstance(MonitorDemo.class).toPrintable());
            }
            log.info("同步块后 {}", ClassLayout.parseInstance(MonitorDemo.class).toPrintable());
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
        }

        Thread t2 = new Thread(() -> {
            log.info("同步前 {}", ClassLayout.parseInstance(MonitorDemo.class).toPrintable());
            synchronized (MonitorDemo.class) {
                log.info("同步块中 {}", ClassLayout.parseInstance(MonitorDemo.class).toPrintable());
            }
            log.info("同步块后 {}", ClassLayout.parseInstance(MonitorDemo.class).toPrintable());
        }, "t2");

        t2.start();

        try {
            //确保T3运行的时候，不存在竟态
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
        }

        Thread t3 = new Thread(() -> {
            log.info("同步前 {}", ClassLayout.parseInstance(MonitorDemo.class).toPrintable());
            synchronized (MonitorDemo.class) {
                log.info("同步块中 {}", ClassLayout.parseInstance(MonitorDemo.class).toPrintable());
            }
            log.info("同步块后 {}", ClassLayout.parseInstance(MonitorDemo.class).toPrintable());
        }, "t3");

        t3.start();
    }
}
