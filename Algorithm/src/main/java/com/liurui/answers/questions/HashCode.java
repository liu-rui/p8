package com.liurui.answers.questions;

/**
 * hashcode生成方式
 * 正确方式：使用素数31连接各个属性
 */
public class HashCode {
    private int id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HashCode hashCode = (HashCode) o;

        if (id != hashCode.id) {
            return false;
        }
        return name.equals(hashCode.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
