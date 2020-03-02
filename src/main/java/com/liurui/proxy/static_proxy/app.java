package com.liurui.proxy.static_proxy;

import com.liurui.proxy.UserService;
import com.liurui.proxy.UserServiceImpl;

/**
 * @author liu-rui
 * @date 2020/3/2 上午10:01
 * @description
 * @since
 */
public class app {
    public static void main(String[] args) {
        UserService target = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy(target);
        proxy.add("张三");
    }
}
