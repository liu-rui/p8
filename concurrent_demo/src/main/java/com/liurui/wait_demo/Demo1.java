package com.liurui.wait_demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 验证notify,notifyall
 * 场景：
 * 共享资源房间(Room),小张和其他人需要申请房间来工作，但是小张必须得抽烟才能工作，怎么提高房间的利用率
 * 小张里面：
 * 1. 如果使用sleep，是的锁一直占用，其他人无法使用
 * <p>
 * 结论：使用 wait/notify
 */
@Slf4j(topic = "Demo1")
public class Demo1 {
    static class Room {
    }

    static Room room = new Room();
    static boolean hasCigarette = false; //是否有烟

    public static void main(String[] args) {


        Thread t1 = new Thread(() -> {
            synchronized (room) {
                if (!hasCigarette) {
                    try {
                        log.info("没烟，等待");
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("干活了");
            }
        }, "小张");

        t1.start();

        for (int i = 0; i < 5; i++) {
            Thread tmp = new Thread(() -> {
                synchronized (room) {
                    log.info("干活了");
                }
            }, "其他人" + i);

            tmp.start();
        }

        Thread t2 = new Thread(() -> {
            try {
                //送外卖需要5s
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
            }
            hasCigarette = true;

            synchronized (room) {
                room.notifyAll();
            }
        }, "送外卖");

        t2.start();
    }
}
