package com.liurui;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        final ServiceLoader<Sendable> load = ServiceLoader.load(Sendable.class);

          Iterator<Sendable> iterator = load.iterator();

        if(iterator.hasNext()){
            System.out.println( iterator.next().getClass());
        }

        iterator = load.iterator();

        if(iterator.hasNext()){
            System.out.println( iterator.next().getClass());
        }
    }
}
