package com.liurui.sync_tools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author liu-rui
 * @date 2020/5/20 下午2:55
 * @description 实现读写锁
 * @since
 */
public class Demo16 {
    static class MyReadWriteLock implements ReadWriteLock {

        @Override
        public Lock readLock() {
            return null;
        }

        @Override
        public Lock writeLock() {
            return null;
        }
    }

}
