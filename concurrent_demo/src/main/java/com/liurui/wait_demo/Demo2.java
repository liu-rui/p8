package com.liurui.wait_demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/4/16 上午10:30
 * @description 场景：有个共用的房间Room（锁），小张和小红都要使用，小张需要烟才能工作，小红需要巧克力才能工作；
 * 外卖到了，送来了巧克力，会唤醒小张，造成虚假唤醒问题。
 * <p>
 * 解决虚假唤醒问题
 * @since
 */
@Slf4j(topic = "Demo2")
public class Demo2 {
    static class Room {
    }

    static Room room = new Room();
    static boolean hasCigaratte = false;
    static boolean hasChocolate = false;


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (room) {
                while (!hasCigaratte) {
                    try {
                        log.info("烟未到，去休息室等待");
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("干活了");
            }
        }, "小张");

        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (room) {
                while (!hasChocolate) {
                    try {
                        log.info("巧克力未到，去休息室等待");
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("干活了");
            }
        }, "小红");

        t2.start();

        Thread t3 = new Thread(() -> {
            try {
                //送外卖需要2s
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hasChocolate = true; //巧克力到了
            synchronized (room) {
                room.notifyAll(); //唤醒所有线程；notify方法会随机一个。都会造成虚假唤醒问题
            }
        }, "送外卖");

        t3.start();
    }
}
