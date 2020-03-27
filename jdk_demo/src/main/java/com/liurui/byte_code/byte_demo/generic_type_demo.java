package com.liurui.byte_code.byte_demo;

/**
 * @author liu-rui
 * @date 2020/3/27 下午2:26
 * @description 泛型测试
 * 通过字节码可以发现：
 * 1. 方法或成员变量上的泛型被擦除，统一使用Object类型，通过attribute的Signature来标记泛型信息
 * 2. 局部变量上的泛型也被擦除，统一使用Object类型，通过attribute的LocalVariableTypeTable来标记泛型信息
 * 3. 泛型是编译器支持的功能，是个语法糖
 * @since
 */
public class generic_type_demo<B> {
    private B a;

    public B getA() {
        return a;
    }

    public void setA(B a) {
        this.a = a;
    }
}
