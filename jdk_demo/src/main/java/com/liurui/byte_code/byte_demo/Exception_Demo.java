package com.liurui.byte_code.byte_demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

/**
 *java字节码对于异常的处理方式：
 * 1.统一采用异常表的方式来对异常进行处理
 * 2.在jdk1.4.2之前的版本中，并不是使用异常表的方式来对异常进行处理的，而是采用特定的指令方式。
 * 3.当异常处理存在finally语句块时，现代化的JVM采用的处理方式是将finally语句块的字节码拼接到每一个catch块后面。
 * 换句话说，程序中存在多少个catch块，就会在每一个catch块后面重复多少个finally语句块的字节码。
 */
public class Exception_Demo {
    public void test() {
        try {
            InputStream is = new FileInputStream("1.txt");
            ServerSocket socket = new ServerSocket(342);

            socket.accept();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } catch (Exception e) {

        } finally {
            System.out.println("finally");
        }
    }

}
