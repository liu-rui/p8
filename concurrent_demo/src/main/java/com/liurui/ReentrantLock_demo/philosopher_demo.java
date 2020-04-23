package com.liurui.ReentrantLock_demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//哲学家问题
//5个哲学家，使用筷子吃饭，一共只有5只筷子
//死锁问题
//解决方案：
// 1. 资源有序分配法；筷子分配按照顺序分配，最后一个人张5改下，从C5,C1改成C1,C5;存在的问题，会有饥饿问题
// 2. 获取锁增加超时，超时了自动释放自己获得的锁。使用ReentrantLock

@Slf4j
public class philosopher_demo {
    @AllArgsConstructor
    static class Philosopher {
        private String name;
        private chopsticks left;
        private chopsticks right;

        public void start() {
            new Thread(() -> {
                while (true) {
                    if (!left.tryLock()) {
                        continue;
                    }

                    try {
                        if (!right.tryLock()) {
                            continue;
                        }

                        try {
                            log.info("吃饭");
                        } finally {
                            right.unlock();
                        }
                    } finally {
                        left.unlock();
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            }, name).start();
        }
    }

    @AllArgsConstructor
    @Getter
    @ToString
    static class chopsticks extends ReentrantLock {
        private String name;
    }


    public static void main(String[] args) {
        chopsticks c1 = new chopsticks("1");
        chopsticks c2 = new chopsticks("1");
        chopsticks c3 = new chopsticks("1");
        chopsticks c4 = new chopsticks("1");
        chopsticks c5 = new chopsticks("1");
        new Philosopher("张1", c1, c2).start();
        new Philosopher("张2", c2, c3).start();
        new Philosopher("张3", c3, c4).start();
        new Philosopher("张4", c4, c5).start();
        new Philosopher("张5", c5, c1).start();
    }
}
