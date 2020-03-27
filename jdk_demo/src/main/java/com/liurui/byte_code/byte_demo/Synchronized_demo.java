package com.liurui.byte_code.byte_demo;

/**
 * synchronized关键字字节码分析
 * 1. 修饰方法的时，字节码中会通过访问修饰符修饰在方法上
 * 2. 修饰代码块时，会插入monitorenter和monitorexit助记符
 * 3. 执行monitorenter和monitorexit助记符时，要求栈顶是拥有监视器对象的引用，执行完monitorenter和monitorexit会弹出这个对象引用
 */
public class Synchronized_demo {

    public synchronized String a() {
        return "aaaa";
    }

    public boolean b() {
        synchronized (Synchronized_demo.class) {
            return false;
        }
    }
}
