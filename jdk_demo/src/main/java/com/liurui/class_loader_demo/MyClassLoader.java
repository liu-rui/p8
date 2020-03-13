package com.liurui.class_loader_demo;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author liu-rui
 * @date 2020/3/13 上午9:47
 * @description
 * @since
 */
public class MyClassLoader extends ClassLoader {
    private String dir;

    public MyClassLoader(String dir) {
        this.dir = dir;
    }

    public MyClassLoader(ClassLoader parent, String dir) {
        super(parent);
        this.dir = dir;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        final byte[] bytes = getClassCache(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] getClassCache(String name) {
        final Path path = Paths.get(dir, name.replace('.', File.separatorChar) + ".class");

        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader classLoader = new MyClassLoader("/home/liurui/文档");

        final Class<?> carClass = classLoader.loadClass("com.liurui.class_loader_demo.Car");
        final Object instance = carClass.newInstance();
        System.out.println(instance);
        System.out.println(instance.getClass().getClassLoader());
    }
}
