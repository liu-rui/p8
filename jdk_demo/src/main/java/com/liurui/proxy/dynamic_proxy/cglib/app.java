package com.liurui.proxy.dynamic_proxy.cglib;

import com.liurui.proxy.UserService;
import com.liurui.proxy.UserServiceImpl;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author liu-rui
 * @date 2020/3/2 下午5:46
 * @description
 * @since
 */
public class app {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "cglib_tmp");
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new MyMethodInterceptor());
        final UserService proxy = (UserService) enhancer.create();

        proxy.add("六二");
    }
}
