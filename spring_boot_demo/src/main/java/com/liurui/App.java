package com.liurui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * Hello world!
 * 用于分析spring boot 启动原理
 * 操作过程:
 * 1. 执行 mvn package
 * 2. 将jar解压到特定的文件夹下
 * 3. shell 跳转到文件夹下，使用tree
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        //打印代码路径
        System.out.println( App.class.getProtectionDomain().getCodeSource().getLocation());
        System.out.println( String.class.getProtectionDomain().getCodeSource().getLocation());
    }
}
