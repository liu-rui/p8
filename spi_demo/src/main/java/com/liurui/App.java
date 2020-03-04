package com.liurui;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        final ServiceLoader<Sendable> load = ServiceLoader.load(Sendable.class);

        final Iterator<Sendable> iterator = load.iterator();

        while (iterator.hasNext()) {
            iterator.next().send();
        }
    }
}
