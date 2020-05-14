package com.liurui.Thread_Pool_Demo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liu-rui
 * @date 2020/5/6 下午3:49
 * @description 自己实现一个线程池
 * @since
 */
@Slf4j
public class Demo1 {
    public static void main(String[] args) {
        MyPool myPool = new MyPool(1, 1, 1, TimeUnit.SECONDS);

        for (int i = 0; i < 3; i++) {
            final int j = i;
            myPool.exec(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                }
                log.info("{}", j);
            });
        }

        System.out.println("main terminated");
    }


    static class MyPool {
        final MyBlockQueue<Runnable> myBlockQueue;
        final Set<Worker> workers = new HashSet<>();
        final private int core;
        private long timeout;
        private TimeUnit timeUnit;

        public MyPool(int core, int queueSize, long timeout, TimeUnit timeUnit) {
            this.core = core;
            this.timeout = timeout;
            this.timeUnit = timeUnit;
            myBlockQueue = new MyBlockQueue<>(queueSize);
        }

        public void exec(Runnable runnable) {
            synchronized (this) {
                if (workers.size() >= core) {
                    myBlockQueue.push(runnable);
                } else {
                    log.info("创建新工作者");
                    Worker worker = new Worker(runnable);

                    workers.add(worker);
                    worker.start();
                }
            }
        }

        class Worker {
            private Runnable runnable;

            public Worker(Runnable runnable) {
                this.runnable = runnable;
            }

            public void start() {
                new Thread(() -> {
                    while (runnable != null || (runnable = myBlockQueue.poll(timeout, timeUnit)) != null) {
                        log.info("执行任务");
                        runnable.run();
                        runnable = null;
                    }
                    log.info("工作者退出");
                    workers.remove(this);
                }).start();
            }
        }
    }


    static class MyBlockQueue<T> {
        Deque<T> lists = new ArrayDeque<>();
        ReentrantLock lock = new ReentrantLock();
        Condition full = lock.newCondition();
        Condition empty = lock.newCondition();
        int size;

        public MyBlockQueue(int size) {
            this.size = size;
        }

        public void push(T runnable) {
            lock.lock();

            try {
                while (lists.size() == size) {
                    try {
                        log.info("等待放入队列....");
                        full.await();
                    } catch (InterruptedException e) {
                    }
                }
                log.info("放入队列 ");
                lists.add(runnable);
                empty.signal();
            } finally {
                lock.unlock();
            }
        }

        public T poll() {
            lock.lock();

            try {
                while (lists.isEmpty()) {
                    try {
                        log.info("队列空了，等待任务。。");
                        empty.await();
                    } catch (InterruptedException e) {
                    }
                }
                log.info("获取到任务");
                final T runnable = lists.removeLast();

                full.signal();
                return runnable;
            } finally {
                lock.unlock();
            }
        }

        public T poll(long timeout, TimeUnit timeUnit) {
            lock.lock();

            try {
                long nanos = timeUnit.toNanos(timeout);

                while (lists.isEmpty()) {
                    try {
                        if (nanos <= 0) {  //等待超时，直接返回
                            return null;
                        }
                        log.info("队列空了，等待任务。。");
                        nanos = empty.awaitNanos(nanos);
                    } catch (InterruptedException e) {
                    }
                }
                log.info("获取到任务");
                final T runnable = lists.removeLast();

                full.signal();
                return runnable;
            } finally {
                lock.unlock();
            }
        }
    }
}