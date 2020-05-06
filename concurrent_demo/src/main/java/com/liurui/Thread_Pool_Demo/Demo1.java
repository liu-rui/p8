package com.liurui.Thread_Pool_Demo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
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
        MyPool myPool = new MyPool(2);

        for (int i = 0; i < 10; i++) {
            final int j = i;
            myPool.exec(() -> {
                log.info("{}", j);
            });
        }
    }


    static class MyPool {
        MyBlockQueue myBlockQueue = new MyBlockQueue(5);
        Set<Worker> workers = new HashSet<>();
        private int core;

        public MyPool(int core) {
            this.core = core;
        }

        public void exec(Runnable runnable) {
            synchronized (this) {
                if (workers.size() >= core) {
                    myBlockQueue.push(runnable);
                } else {
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
                    while (runnable != null || (runnable = myBlockQueue.poll()) != null) {
                        runnable.run();
                        runnable = null;
                    }
                    workers.remove(this);
                }).start();
            }
        }
    }


    static class MyBlockQueue {
        Deque<Runnable> lists = new ArrayDeque<>();
        ReentrantLock lock = new ReentrantLock();
        Condition full = lock.newCondition();
        Condition empty = lock.newCondition();
        int size;

        public MyBlockQueue(int size) {
            this.size = size;
        }

        public void push(Runnable runnable) {
            lock.lock();

            try {
                while (lists.size() == size) {
                    try {
                        log.info("满了");
                        full.await();
                    } catch (InterruptedException e) {
                    }
                }

                lists.add(runnable);
                empty.signal();
            } finally {
                lock.unlock();
            }
        }

        public Runnable poll() {
            lock.lock();

            try {
                while (lists.isEmpty()) {
                    try {
                        log.info("空了");
                        empty.await();
                    } catch (InterruptedException e) {
                    }
                }

                final Runnable runnable = lists.removeLast();

                full.signal();
                return runnable;
            } finally {
                lock.unlock();
            }
        }
    }
}
