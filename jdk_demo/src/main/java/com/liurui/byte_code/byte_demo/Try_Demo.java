package com.liurui.byte_code.byte_demo;

/**
 * @author liu-rui
 * @date 2020/5/15 上午11:20
 * @description
 * @since
 */
public class Try_Demo {
    public int test() throws Exception {
        int a = 10;
        try (AutoClose autoClose = new AutoClose()) {
            a = 5;
        } finally {
            a = 3;
        }

        return a;
    }

    static class AutoClose implements AutoCloseable {

        @Override
        public void close() throws Exception {

        }
    }

}
