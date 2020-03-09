package com.liurui.class_loader_demo.singlon_demo;

/**
 * 类需要经过装载，连接和初始化过程。
 * 连接中包括：验证，准备和解析
 * 准备阶段将变量赋值为默认值
 * a=0,b=0
 * 初始化阶段按照顺序执行
 * a=1
 * 实例化singlon,调用构造函数，a=2,b=1
 * b=0
 *
 * 最终结果是a=2,b=0
 *
 * 视频地址：https://www.bilibili.com/video/av75247289?p=8
 */
public class app {
    public static void main(String[] args) {
        Singlon singlon = Singlon.getInstance();
        System.out.println("---------");
        System.out.println(Singlon.a);
        System.out.println(Singlon.b);
    }

    public static class Singlon {
        public static int a = 1;

        private static Singlon singlon = new Singlon();

        private Singlon() {
            a++;
            b++;
            System.out.println(a);
            System.out.println(b);
        }

        public static int b = 0;

        public static Singlon getInstance() {
            return singlon;
        }
    }

}
