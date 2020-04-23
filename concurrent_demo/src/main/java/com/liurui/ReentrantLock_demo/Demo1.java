package com.liurui.ReentrantLock_demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//
//同一间屋子,小张需要烟才能工作，小红需要巧克力才能工作。
//送外卖送来了巧克力，唤醒小红工作
//通过使用ReentrantLock的不同的条件变量，避免虚假唤醒问题。
@Slf4j
public class Demo1 {
    static ReentrantLock room = new ReentrantLock();
    static Condition SmokeArea = room.newCondition();
    static Condition diningHall = room.newCondition();


    public static void main(String[] args) {


        new Thread(() -> {
            room.lock();

            try {
                log.info("没烟，去抽烟区等待");
                try {
                    SmokeArea.await();
                } catch (InterruptedException e) {
                }
                log.info("有烟了，继续工作");

            } finally {
                room.unlock();
            }

        }, "小张").start();

        new Thread(() -> {
            room.lock();

            try {
                log.info("没有巧克力，去餐厅等待");
                try {
                    diningHall.await();
                } catch (InterruptedException e) {
                }
                log.info("有巧克力了，继续工作");
            } finally {
                room.unlock();
            }
        }, "小红").start();


        new Thread(() -> {
            try {
                //送外卖需要2s
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            room.lock();
            try {
                diningHall.signalAll();
            } finally {
                room.unlock();
            }

        }, "送外卖").start();
    }
}
