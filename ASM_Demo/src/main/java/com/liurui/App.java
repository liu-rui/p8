package com.liurui;

import jdk.internal.org.objectweb.asm.ClassReader;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        MyClassPrinter myClassPrinter = new MyClassPrinter();
        ClassReader classReader = new ClassReader("java.lang.Runnable");

        classReader.accept(myClassPrinter, 0);

    }
}
