package com.liurui.gc;

/**
 * 目标：分析GC日志
 * <p>
 * -Xms20m
 * -Xmx20m
 * -Xmn10m
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps
 * -XX:SurvivorRatio=8
 * -XX:+PrintHeapAtGC
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPaht=./heap_dump.hprof
 * <p>
 * 按照上面的配置，结果为：
 * 1. 新生代：10m,其中eden 8m
 * 2. 老年代：10m
 * 3. 启动后，系统会占用3m(jstat工具attach产生的),这样的话，eden还剩5m可用
 * <p>
 * 结论：
 * 1. 新生代放不下会直接进入老年代；如：直接放入8m字节数组
 * 2.-verbose:gc等同于-XX:+PrintGC,输出gc执行后的回收情况和耗时；使用-XX:+PrintGCDetails更清楚些
 * 3. -XX:+PrintGCTimeStamps等同于-XX:+PrintGC,没有详细的时间，以后统一使用-XX:+PrintGCDateStamps
 *
 */
public class demo1 {
    static int len = 1024 * 1024;

    public static void main(String[] args) {
        Allocate();
    }


    /**
     * 8m,eden一共才8m,系统会占用3m,这样才剩下5m,放不下8m,直接放入了年老代
     */
    private static void bigData() {
        byte[] a = new byte[5 * len];

        return;
    }

    private static void Allocate() {
        //启动后，eden占用3m,还剩5m可用
        //5m可用，4m能放入， 放入了eden中，还剩1m
        System.out.println("----------------------a*4");
        byte[] a = new byte[4 * len];
        System.out.println("----------------------b*2");
        //1m可用，2m放不下，进行minor gc后，放入了eden中
        byte[] b = new byte[2 * len];
        System.out.println("----------------------c*2");
        byte[] c = new byte[20 * len];
        return;
    }
}
