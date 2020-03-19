package com.liurui.class_loader_demo.interface_demo;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liu-rui
 * @date 2020/3/10 上午11:21
 * @description
 * @since
 */
public class app {
    public static void main(String[] args) {
        final Child child = new Child();
        Parent parent = (Parent) child;
        child.get();
        System.out.println(Parent.a);
    }

    public interface Parent {
        public static final Thread ParentStr = new Thread() {
            {
                System.out.println("parent ");
            }
        };

        void get();

        int a = ThreadLocalRandom.current().nextInt();
    }

    public static class Child implements Parent {
        public static final Thread ChildStr = new Thread() {
            {
                System.out.println("child ");
            }
        };

        @Override
        public void get() {
            System.out.println("get");
        }
    }
}


