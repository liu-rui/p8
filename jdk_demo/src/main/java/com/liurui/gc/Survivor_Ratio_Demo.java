package com.liurui.gc;

import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/4/1 上午10:20
 * @description
 * 验证默认VM参数
 * 当前服务器物理内存为16G
 * 使用 jcmd 19917 VM.flags
 *-XX:CICompilerCount=4 -XX:InitialHeapSize=262144000 -XX:MaxHeapSize=4175429632 -XX:MaxNewSize=1391460352
 * -XX:MinHeapDeltaBytes=524288 -XX:NewSize=87031808 -XX:OldSize=175112192
 * -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC
 * 结论：
 *  1. -XX:+UseParallelGC 使用了并行垃圾回收器： parallel scavenge  + parallel old 组合
 *  2. 最大堆是4175429632，大概有4G,占总内存的1/4，默认参数无法充分利用内存,建议是在1/2到2/3之间，10G合适。
 *  4. 堆大小初始值与最大值不相等，默认值是1:2,不准确。年轻代和年老代会动态扩容，比例也不是固定的，会达到1：116。
 *  4. 官方文档上说Eden和Survivor默认比例是8:1,通过实验发现：不是的，比例动态调整，会达到45:1,；S0和S1空间会出现不相等的情况。
 *
 * 强制指定比例再次验证下：
 * -verbose:gc -Xms4g -Xmx4g -XX:SurvivorRatio=8 -XX:NewRatio=2 -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 * 结论：
 * 1. 年老代和年轻代大小按照2:1比例分配4g空间
 * 2. eden和survivor比例为8
 *
 *
 *
 * 最终结论： 如果不配置比例的话，都会动态调整，官方给的默认值不准确。所以需要强制指定下才能生效。
 *
 * 分析工具：
 * 1. jcmd pid vm.flags 获取vm参数
 * 2. jstat -gc pid 5s  每5秒获取gc的分配情况
 * 3. jstat -gcutil pid 5s  每5秒获取gc的分配比例
 *
 */
public class Survivor_Ratio_Demo {

    private static  void a(){
        int mb = 1024  * 1024;


        byte[] a = new byte[10 * mb];

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a();
    }

    public static void main(String[] args) {
        a();
    }
}