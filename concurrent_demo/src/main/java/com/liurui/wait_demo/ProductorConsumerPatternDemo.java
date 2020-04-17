package com.liurui.wait_demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/4/17 上午10:57
 * @description 生产者消费者模式
 * @since
 */
public class ProductorConsumerPatternDemo {

    @Slf4j(topic = "Productor")
    static class Productor {
        private String name;
        private MessageQueue messageQueue;

        public Productor(String name, MessageQueue messageQueue) {
            this.name = name;
            this.messageQueue = messageQueue;
        }

        public void start() {
            Thread thread = new Thread(() -> {
                for (int i = 0; i < 2; i++) {
                    try {
                        //1秒中产生一个消息
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    final Message message = new Message(String.format("%s %s", name, i));

                    messageQueue.put(message);
                    log.info("产生消息： {}", message);
                }
            }, name);
            thread.start();
        }
    }

    @Slf4j(topic = "Consumer")
    static class Consumer {
        private String name;
        private MessageQueue messageQueue;

        public Consumer(String name, MessageQueue messageQueue) {
            this.name = name;
            this.messageQueue = messageQueue;
        }

        public void start() {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        //1秒中消费一个消息
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    log.info("消费消息: {}", messageQueue.get());
                }
            }, name);
            thread.start();
        }
    }

    static class MessageQueue {
        private LinkedList<Message> list = new LinkedList<>();
        private int capacity;

        public MessageQueue(int capacity) {
            this.capacity = capacity;
        }

        public synchronized void put(Message message) {
            while (isFull()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(message);
            this.notifyAll();
        }

        public synchronized Message get() {
            while (isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            final Message message = list.removeLast();

            this.notifyAll();
            return message;
        }

        private boolean isFull() {
            return list.size() == capacity;
        }

        private boolean isEmpty() {
            return list.isEmpty();
        }
    }

    @Getter
    @AllArgsConstructor
    @ToString
    static class Message {
        private String message;
    }


    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(2);

        for (int i = 0; i < 2; i++) {
            new Productor("生产者" + i, messageQueue).start();
        }
        new Consumer("消费者", messageQueue).start();
    }
}
