package com.liurui.CAS_Demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/4/27 下午2:42
 * @description 验证happen-before中的传递性
 * @since
 */
@Slf4j
public class Happen_Before_Demo {
    volatile static long a = 100;
    volatile static long b = 200;

    public static void main(String[] args) {
        log.info("start");

        new Thread(() -> {
            while (a == 100 || b == 200) {

            }
            log.info("t1 end! a:{} b: {}", a, b);
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }

        new Thread(() -> {
            a = 200;
            b = 400;
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
            }
        }, "t1").start();
    }
}
