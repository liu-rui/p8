package com.liurui.sync_tools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author liu-rui
 * @date 2020/5/19 下午5:42
 * @description 自己实现一个CountDownLatch
 * 实现步骤：
 * 1. MyLatch提供一个构造参数，传递数量
 * 2. 定义2个方法：await和countDown方法
 * 3. 定义一个内部类sync继承AQS,使用共享锁，实现方法tryAcquireShared和tryReleaseShared
 * @since
 */
@Slf4j
public class Demo44 {
    public static void main(String[] args) {
        MyLatch latch = new MyLatch(2);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                log.info("doing");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                }
                latch.countDown();
            }, "t" + i).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                log.info("finish");
            }, "a" + i).start();
        }
    }
}


class MyLatch {
    private Sync sync;

    public MyLatch(int count) {
        sync = new Sync(count);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    public void countDown() {
        sync.releaseShared(1);
    }


    static class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            this.setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return getState() == 0 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                final int state = getState();

                if (state == 0) {
                    return false;
                }
                final int newState = state - 1;

                if (compareAndSetState(state, newState)) {
                    return newState == 0;
                }
            }
        }
    }
}
