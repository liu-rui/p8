package com.liurui.sync_tools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 通过继承AQS实现一个不可重复的独占锁
 */
@Slf4j
public class Demo1 {
    public static void main(String[] args) {
        MyLock lock = new MyLock();

        new Thread(() -> {
            lock.lock();

            try {
                log.info("获取锁");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            } finally {
                log.info("释放锁");
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();

            try {
                log.info("获取锁");
            } finally {
                log.info("释放锁");
                lock.unlock();
            }
        }, "t2").start();
    }

    static class MyLock implements Lock {

        static class MySync extends AbstractQueuedSynchronizer {
            @Override
            protected boolean tryAcquire(int arg) {
                if (compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
                return false;
            }

            @Override
            protected boolean tryRelease(int arg) {
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }

            @Override
            protected boolean isHeldExclusively() {
                return getExclusiveOwnerThread() == Thread.currentThread();
            }

            public Condition newCondition() {
                return new ConditionObject();
            }
        }

        MySync sync = new MySync();

        @Override
        public void lock() {
            sync.acquire(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            sync.acquireInterruptibly(1);
        }

        @Override
        public boolean tryLock() {
            return sync.tryAcquire(1);
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return sync.tryAcquireNanos(1, unit.toNanos(time));
        }

        @Override
        public void unlock() {
            sync.release(1);
        }

        @Override
        public Condition newCondition() {
            return sync.newCondition();
        }
    }

}
