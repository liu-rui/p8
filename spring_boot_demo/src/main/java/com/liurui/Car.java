package com.liurui;

/**
 * @author liu-rui
 * @date 2020/3/5 下午5:36
 * @description
 * @since
 */
public class Car {
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
