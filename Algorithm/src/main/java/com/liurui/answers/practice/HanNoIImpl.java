package com.liurui.answers.practice;

import com.liurui.defines.practice.HanNoI;

import java.util.ArrayList;
import java.util.Stack;

public class HanNoIImpl implements HanNoI {
    @Override
    public String[] move(int sum, char a, char b, char c) {
        return answer2(sum, a, b, c);
    }

    /**
     * 使用栈实现
     *
     * @param sum
     * @param a
     * @param b
     * @param c
     * @return
     */
    private String[] answer1(int sum, char a, char b, char c) {
        ArrayList<String> container = new ArrayList<>();
        Stack<Item> stack = new Stack<>();
        stack.push(new Item(sum, a, b, c));
        while (!stack.empty()) {
            Item item = stack.pop();

            if (item.num == 1) {
                move(container, item.a, item.c);
            } else {
                stack.push(new Item(1, a, b, c));
                stack.push(new Item(item.num - 1, a, c, b));
            }
        }

        return container.toArray(new String[0]);
    }

    private static class Item {
        private int num;
        private char a, b, c;

        public Item(int num, char a, char b, char c) {
            this.num = num;
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }


    /**
     * 使用递归实现
     *
     * @param sum
     * @param a
     * @param b
     * @param c
     * @return
     */
    private String[] answer2(int sum, char a, char b, char c) {
        ArrayList<String> container = new ArrayList<>();

        move(container, sum, a, b, c);
        return container.toArray(new String[0]);
    }


    private void move(ArrayList<String> container, int sum, char a, char b, char c) {
        if (sum == 1) {
            move(container, a, c);
            return;
        }

        move(container, sum - 1, a, c, b);
        move(container, a, c);
        move(container, sum - 1, b, a, c);
    }

    private void move(ArrayList<String> container, char a, char b) {
        container.add(String.format("%c -> %c", a, b));
    }
}
