package com.liurui;

/**
 * @author liu-rui
 * @date 2020/3/4 下午3:07
 * @description
 * @since
 */
public class RocketSender  implements  Sendable {
    @Override
    public void send() {
        System.out.println("rocket  send");
    }
}
