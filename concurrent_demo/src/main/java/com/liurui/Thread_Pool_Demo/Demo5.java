package com.liurui.Thread_Pool_Demo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liu-rui
 * @date 2020/5/12 下午3:47
 * @description 场景：
 * 主线程产生5个数，消费者接收到数据后打印；统计哪些消费者全部消费了
 * @since
 */
@Slf4j
public class Demo5 {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        Thread t1 = null;
        Thread t2 = null;
        try {
            t1 = new Thread(() -> {
                int i = 0;
                while (true) {
                    if (i == 2) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info(queue.poll(0).toString());
                    i++;
                }
            }, "consumer1");

            t1.start();

            t2 = new Thread(() -> {
                while (true) {
                    log.info(queue.poll(1).toString());
                }
            }, "consumer2");

            t2.start();

            for (int i = 0; i < 5; i++) {
                final boolean push = queue.push(new Data(i), 1, TimeUnit.SECONDS);
                log.info("push {} ret : {}", i, push);

                if (!push) {
                    return;
                }
            }
        } finally {
            t1.interrupt();
            t2.interrupt();
            t1.stop();
            t2.stop();
        }
    }

    @lombok.Data
    @AllArgsConstructor
    static class Data {
        private int i;
    }

    static class MyQueue {
        volatile Data data = null;
        ReentrantLock lock = new ReentrantLock();
        Condition producerCondition = lock.newCondition();
        Condition consumerCondition = lock.newCondition();
        AtomicIntegerArray thread = new AtomicIntegerArray(new int[2]);

        public boolean push(Data data, long timeout, TimeUnit timeUnit) {
            lock.lock();
            try {
                while (this.data != null) {
                    if (!producerCondition.await(timeout, timeUnit)) {
                        log.info(thread.toString());
                        return false;
                    }
                }
                this.data = data;
                consumerCondition.signalAll();
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
            }
            return true;
        }

        public Data poll(int index) {
            lock.lock();

            try {
                while (this.data == null || thread.get(index) == 1) {
                    consumerCondition.await();
                }
                thread.set(index, 1);

                boolean a = false;

                for (int i = 0; i < thread.length(); i++) {
                    if (thread.get(i) == 0) {
                        a = true;
                    }
                }
                if (a) {
                    return data;
                } else {
                    for (int i = 0; i < thread.length(); i++) {
                        thread.set(i, 0);
                    }
                    Data ret = data;
                    data = null;
                    producerCondition.signal();
                    return ret;
                }
            } catch (InterruptedException e) {
                return null;
            } finally {
                lock.unlock();
            }
        }
    }
}
