package com.liurui.blocking_queue_demo;

import com.sun.tools.javac.util.Assert;

import java.util.HashMap;

/**
 * HashMap
 */
public class Demo41 {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>(4);

        map.put(3, "ren");
        Assert.check("ren".equals(map.put(3, "xss")));//put方法返回旧值，ｋｅｙ不存在时返回null
        map.put(7, "aa");
        map.put(1, "bb");
        map.put(2, "cc"); //加断点，此处扩容
    }
}
