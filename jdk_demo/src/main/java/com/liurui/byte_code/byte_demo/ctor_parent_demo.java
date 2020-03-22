package com.liurui.byte_code.byte_demo;

public class ctor_parent_demo {
    private  static  class  Parent{
        public Parent() {
            System.out.println("parent ctor");
        }
    }

    private  static  class  Child extends  Parent{
        public Child() {
            System.out.println("child ctor0");
        }

        public Child(int a ) {
            System.out.println("child ctor1");
        }
    }

    public static void main(String[] args) {
        new Child(1);
    }
}
