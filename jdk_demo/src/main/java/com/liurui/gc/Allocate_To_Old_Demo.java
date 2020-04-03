package com.liurui.gc;

/**
 * @author liu-rui
 * @date 2020/4/3 下午5:06
 * @description -XX:PretenureSizeThreshold
 * 对象超过指定大小直接分配到老年代，默认值是0，即不限制。
 *
 * 测试配置此参数后内存分配的效果
 * 基本配置
 * -Xms20m
 * -Xmx20m
 * -Xmn10m
 * -XX:SurvivorRatio=8
 * -XX:+PrintGCDetails
 * -XX:+PrintCommandLineFlogs
 *
 * 1. 使用基本参数
 * 输出： 发现在eden中分配，oldgen为0
 * 2. 添加 -XX:PretenureSizeThreshold=1m
 * 输出： 发现在eden中分配，oldgen为0
 * 3. 添加 -XX:PretenureSizeThreshold=1m -XX:+UseSerialGC
 * 输出： 发现在oldgen中分配，oldgen为2048
 * 4. 添加 -XX:PretenureSizeThreshold=1m -XX:+UseConcMarkSweepGC
 * 输出： 发现在oldgen中分配，oldgen为2048
 * 5. 添加 -XX:PretenureSizeThreshold=1m -XX:+UseG1GC
 * 输出： TODO: 输出待分析
 *
 * 总结：
 * PretenureSizeThreshold参数
 * 在，UseParallelGC（默认也是这个）下是不支持的
 * 在 UseSerialGC，UseConcMarkSweepGC下支持
 *
 * UseConcMarkSweepGC中，年轻代使用ParNew
 *
 * 原理：
 * PretenureSizeThreshold参数属于年轻代算法
 * 年轻代的收集器有：Serial,ParNew,ParallelScavenge和G1
 * 其中支持的有：Serial,ParNew
 * 不支持的：ParallelScavenge
 * TODO： G1待分析
 *
 * @since
 */
public class Allocate_To_Old_Demo {
    public static void main(String[] args) {
        int m1 = 1024 * 1024;

        byte[] a = new byte[2 * m1];
    }
}
