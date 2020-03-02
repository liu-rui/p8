package com.liurui.proxy.static_proxy;

import com.liurui.proxy.UserService;

/**
 * @author liu-rui
 * @date 2020/3/2 上午10:01
 * @description
 * @since
 */
public class UserServiceProxy implements UserService {
    private UserService userService;

    public UserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void add(String name) {
        System.out.println("准备向数据库中插入数据");
        userService.add(name);
        System.out.println("插入数据库成功");
    }
}
