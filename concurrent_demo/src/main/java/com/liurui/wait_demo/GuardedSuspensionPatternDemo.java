package com.liurui.wait_demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/4/16 上午11:17
 * @description 同步模式之保护性暂停模式
 * 功能：一个线程等待另外一个线程的执行结果
 * <p>
 * <p>
 * 举例：
 * 妈妈做饭产生食物，孩子等待食物
 * @since
 */
@Slf4j(topic = "GuardedSuspensionPatternDemo")
public class GuardedSuspensionPatternDemo {
    static class GuardedSuspension {
        private String food; //食物

        public String getFood(int timeout) {
            long start = System.currentTimeMillis();
            long sleep = timeout;

            while (food == null) {
                if (sleep <= 0) {
                    break;
                }

                synchronized (this) {
                    try {
                        this.wait(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                sleep = timeout - (System.currentTimeMillis() - start);
            }
            return food;
        }

        public void setFood(String food) {
            this.food = food;
            synchronized (this) {
                this.notifyAll();
            }
        }
    }


    public static void main(String[] args) {
        GuardedSuspension guardedSuspension = new GuardedSuspension();

        Thread monther = new Thread(() -> {
            try {
                log.info("做饭，需要5秒");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            guardedSuspension.setFood(null);
        }, "妈妈");

        monther.start();

        Thread child = new Thread(() -> {
            log.info("获取食物");
            final String food = guardedSuspension.getFood(7000); //分析下时间为2000和7000的结果

            if (food == null) {
                log.info("饭还没有做好，哇哇哭！");
            } else {
                log.info("吃" + food);
            }
        }, "孩子");

        child.start();
    }
}
