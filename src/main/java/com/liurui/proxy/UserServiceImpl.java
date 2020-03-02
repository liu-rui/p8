package com.liurui.proxy;

/**
 * @author liu-rui
 * @date 2020/3/2 上午10:00
 * @description
 * @since
 */
public class UserServiceImpl implements UserService {
    @Override
    public void add(String name) {
        System.out.println("向数据库中插入名为： " + name + " 的用户");
    }
}
