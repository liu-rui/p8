package com.liurui.sync_tools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author liu-rui
 * @date 2020/5/20 下午2:36
 * @description
 * @since
 */
@Slf4j
public class Demo33 {
    public static void main(String[] args) {
        MySemaphore semaphore = new MySemaphore(3);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                semaphore.acquire();

                try {
                    TimeUnit.SECONDS.sleep(2);
                    log.info("{}", finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, "t" + i).start();
        }
    }

    static class MySemaphore {
        private final Sync sync;

        public MySemaphore(int count) {
            this.sync = new Sync(count);
        }

        public void acquire() {
            sync.acquireShared(1);
        }

        public void release() {
            sync.releaseShared(1);
        }

        static class Sync extends AbstractQueuedSynchronizer {
            public Sync(int count) {
                setState(count);
            }

            @Override
            protected int tryAcquireShared(int arg) {
                for (; ; ) {
                    final int state = getState();
                    final int i = state - 1;

                    if (i < 0 || compareAndSetState(state, i)) {
                        return i;//返回剩余资源数量。
                    }
                }
            }

            @Override
            protected boolean tryReleaseShared(int arg) {
                for (; ; ) {
                    final int state = getState();
                    final int i = state + 1;

                    if (compareAndSetState(state, i)) {
                        return i > 0;
                    }
                }
            }
        }
    }
}


