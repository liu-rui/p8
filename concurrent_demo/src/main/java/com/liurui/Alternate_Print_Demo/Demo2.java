package com.liurui.Alternate_Print_Demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liu-rui
 * @date 2020/4/21 下午2:27
 * @description
 * @since
 */
public class Demo2 {
    @Slf4j(topic = "Printer")
    static class Printer {
        static int num = 0;
        static ReentrantLock lock = new ReentrantLock();
        static Condition condition = lock.newCondition();
        int index;
        int maxCount;
        int count = 0;

        public Printer(int index, int maxCount) {
            this.index = index;
            this.maxCount = maxCount;
        }

        public void start() {
            Thread t = new Thread(() -> {
                lock.lock();
                try {
                    while (true) {
                        if (num % 3 == index) {
                            System.out.print((char) (65 + index));
                            num++;
                            condition.signalAll();
                            count++;

                            if (count == maxCount) {
                                break;
                            }
                        } else {
                            try {
                                condition.await();
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                } finally {
                    lock.unlock();
                }

            }, "工作线程" + index);

            t.start();
        }
    }


    public static void main(String[] args) {
        new Demo1.Printer(0, 3).start();
        new Demo1.Printer(1, 3).start();
        new Demo1.Printer(2, 3).start();
    }
}
