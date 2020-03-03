package com.liurui.reference.weak;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author liu-rui
 * @date 2020/3/2 下午6:05
 * @description -Xmx16m  -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:NewSize=2m -XX:MaxNewSize=2m
 * @since
 */
public class ArrayList_App {
    public static class Car {
        private String code;

        public Car(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "code='" + code + '\'' +
                    '}';
        }
    }


    public static void main(String[] args) {
        ArrayList<WeakReference<Car>>  list = new ArrayList<>();
        list.add(new WeakReference<Car>(new Car("23")));

        while (true){
            if(list.get(0).get() == null){
                System.out.println("collected");
                break;
            }else{
                System.out.println(list.get(0).get().toString());
            }
        }
    }
}
