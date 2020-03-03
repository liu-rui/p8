package com.liurui.reference.weak;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author liu-rui
 * @date 2020/3/3 下午4:07
 * @description -Xmx16m  -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:NewSize=2m -XX:MaxNewSize=2m
 * @since
 */
public class HashMap_app {
    public static  class  Car{
       private  String code;

        public  Car(String code){
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
        testWeakHashMap();
    }

    private  static void  testHashMap(){
        HashMap<WeakReference<Car>, WeakReference<Car>> map = new HashMap<>();
        map.put(new WeakReference<>(new Car("1")) , new WeakReference<>(new Car("11")));
        map.put(new WeakReference<>(new Car("2")) , new WeakReference<>(new Car("22")));

        while (true){
            if(map.values().iterator().next().get() == null){
                System.out.println("collected");
                break;
            }
        }

        for (Map.Entry<WeakReference<Car>, WeakReference<Car>> entry : map.entrySet()) {
            System.out.println(String.format("key:%s value:%s",entry.getKey().get() , entry.getValue().get()));
        }
    }

    private static void testWeakHashMap(){
        WeakHashMap<Car , Car> map  = new WeakHashMap<>();
        map.put(new Car("1") ,  new Car("11"));
        map.put(new Car("2") ,  new Car("22"));

        while (true){
             if(map.isEmpty()){
                System.out.println("collected");
                 break;
            }
        }

    }
}
