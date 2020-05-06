package com.liurui.CAS_Demo;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自己实现一个AtomicInteger
 * <p>
 * 功能：
 * 1.设置默认值
 * 2. 添加特定数量
 * 3. 返回值
 */
public class MyAtomicInteger {
    public static void main(String[] args) throws InterruptedException {
        test(new DefaultAccount());
        test(new AccountByAtomicInteger());
        test(new AcountByMyAtomicInteger());
    }

    static void test(Account account) throws InterruptedException {
        account.set(100000);
        Thread[] threads = new Thread[10000];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                account.sub(10);
            }, "t" + (i + 1));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(account.get());
    }

    static interface Account {
        void set(int account);

        int get();

        void sub(int money);
    }


    static class DefaultAccount implements Account {
        int count = 0;

        @Override
        public void set(int account) {
            count = account;
        }

        @Override
        public int get() {
            return count;
        }

        @Override
        public void sub(int money) {
            count -= money;
        }
    }


    static class AccountByAtomicInteger implements Account {
        AtomicInteger atomicInteger = new AtomicInteger();

        @Override
        public void set(int account) {
            atomicInteger.set(account);
        }

        @Override
        public int get() {
            return atomicInteger.get();
        }

        @Override
        public void sub(int money) {
            atomicInteger.getAndAdd(-money);
        }
    }

    static class AcountByMyAtomicInteger implements Account {
        MyAtomic myAtomic = new MyAtomic();


        @Override
        public void set(int account) {
            myAtomic.set(account);
        }

        @Override
        public int get() {
            return myAtomic.get();
        }

        @Override
        public void sub(int money) {
            myAtomic.add(-money);
        }
    }

    static class MyAtomic {
        static long valueOffset;
        static Unsafe unsafe;
        volatile int value;

        static {
            final Class<Unsafe> unsafeClass = Unsafe.class;
            Field theUnsafe = null;
            try {
                theUnsafe = unsafeClass.getDeclaredField("theUnsafe");

                theUnsafe.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            try {
                unsafe = (Unsafe) theUnsafe.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            try {
                valueOffset = unsafe.objectFieldOffset(MyAtomic.class.getDeclaredField("value"));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        public void set(int value) {
            this.value = value;
        }

        public int get() {
            return this.value;
        }

        public void add(int value) {
            unsafe.getAndAddInt(this, valueOffset, value);
        }
    }
}
