package com.liurui.unpark_demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author liu-rui
 * @date 2020/4/17 下午5:39
 * @description
 * @since 验证unpark可以释放正在park或者下一次park
 * <p>
 * <p>
 * 结论：
 * 1. unpark可以对下一次park生效，但只要一次。即无论执行多少次unpark，只对一次park生效。
 */
@Slf4j(topic = "Demo2")
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                //先休息2s,让unpark先执行
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            LockSupport.park();
            log.info("park 第一次成功");
            LockSupport.park();
            log.info("park 第二次成功");
        }, "t");

        t.start();
        LockSupport.unpark(t);
        LockSupport.unpark(t);

        TimeUnit.SECONDS.sleep(4);
        LockSupport.unpark(t);
    }
}
