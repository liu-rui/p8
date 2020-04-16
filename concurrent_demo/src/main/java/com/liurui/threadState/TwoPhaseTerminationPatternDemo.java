package com.liurui.threadState;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/4/16 上午9:50
 * @description
 * 使用两阶段终止模式，提供一个友好的执行器(executor)
 * 1. 提供开始和结束方法
 * 2. 结束后执行器可以做些清理工作
 *
 *
 * @since
 */
@Slf4j(topic = "TwoPhaseTerminationPatternDemo")
public class TwoPhaseTerminationPatternDemo {
    static class Executor {
        private Thread thread;

        public void start() {
            thread = new Thread(this::run, "executor");
            thread.start();
        }

        private void run() {
            while (true) {
                if (thread.isInterrupted()) {
                    clean();
                    return;
                }

                log.info("干活了");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    thread.interrupt();
                }
            }
        }

        //停止后清理工作，比如释放各种资源
        private void clean() {
            log.info("即将终止工作，清理垃圾");
        }

        public void stop() {
            if (thread != null) {
                thread.interrupt();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Executor exe = new Executor();

        exe.start();
        TimeUnit.SECONDS.sleep(5);
        exe.stop();
    }
}
