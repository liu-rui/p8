package com.liurui.proxy.dynamic_proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.LazyLoader;

/**
 * @author liu-rui
 * @date 2020/4/8 下午5:11
 * @description
 * @since
 */
public class LazyLoaderDemo {
    public static class Customer {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        final Customer proxy = (Customer) Enhancer.create(Customer.class,
                null,
                new LazyLoader() {
                    @Override
                    public Object loadObject() throws Exception {
                        System.out.println("load from lazyloader");
                        Customer customer =  new Customer();

                        customer.setName("hello world");
                        return customer;
                    }
                });

        System.out.println(proxy.getClass());
        System.out.println(proxy.getName());
    }
}
