package com.liurui.Alternate_Print_Demo;

import lombok.extern.slf4j.Slf4j;


/**
 * 通过wait & notifyAll
 */
public class Demo1 {

    @Slf4j(topic = "Printer")
    static class Printer {
        static int num = 0;
        int index;
        int maxCount;
        int count = 0;

        public Printer(int index, int maxCount) {
            this.index = index;
            this.maxCount = maxCount;
        }

        public void start() {
            Thread t = new Thread(() -> {
                synchronized (Printer.class) {
                    while (true) {
                        if (num % 3 == index) {
                            System.out.print((char) (65 + index));
                            num++;
                            Printer.class.notifyAll();
                            count++;

                            if (count == maxCount) {
                                break;
                            }
                        } else {
                            try {
                                Printer.class.wait();
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                }
            }, "工作线程" + index);

            t.start();
        }
    }


    public static void main(String[] args) {
        new Printer(1, 3).start();
        new Printer(2, 3).start();
        new Printer(0, 3).start();
    }
}
