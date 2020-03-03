package com.liurui.reference.weak;

import sun.misc.GC;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/3/2 下午3:01
 * @description -Xmx16m  -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:NewSize=2m -XX:MaxNewSize=2m
 * @since
 */
public class app {
    public static class Car {
    }

    public static void main(String[] args) {
        final Car car1 = new Car();
        WeakReference<Car>  car = new WeakReference<>(car1);

        while (true){
            if(car.get() != null){

            }else{
                System.out.println("collected");
                break;
            }
        }
//        System.out.println(car1.toString());

    }
}
