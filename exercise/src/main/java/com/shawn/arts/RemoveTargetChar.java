package com.shawn.arts;


import java.util.LinkedList;
import java.util.Stack;

/**
 * 定义一个函数 用来判断一个句子中的#号 并将#号内的内容删除
 *
 */
public class RemoveTargetChar {
    /**
     * 删除 成对的#之间的字符
     * 要求只能用正好一个stack（堆/栈） 并且对list的处理只能用pop和 append
     * 比如 一个句子
     * 输入i just #maybe# want to #something
     * 输出i just want to #something
     *
     * @param args
     */
    public static void main(String[] args) {
        String input = "#哈哈#123 5#123#";
        System.out.println(remove(input));
        System.out.println(remove2(input));
    }

    private static String remove(String input) {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (char c : input.toCharArray()) {
            if (c == '#' && ++count == 2) {
                while(!stack.isEmpty()) {
                    if (stack.pop() == '#') {
                        count = 0;
                        break;
                    }
                }
            } else {
                stack.add(c);
            }
        }
        Stack<Character> stack2 = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        // 字符前后顺序处理
        while (!stack.isEmpty()) {
            stack2.push(stack.pop());
        }
        while (!stack2.isEmpty()) {
            stringBuilder.append(stack2.pop());
        }
        return stringBuilder.toString();
    }

    private static String remove2(String input) {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '#' && ++count == 2) {
                while(stack.pop() != '#') {
                }
                count = 0;
            } else {
                stack.add(c);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Character> stack2 = new Stack<>();
        while (!stack.isEmpty()) {
            stack2.push(stack.pop());
        }
        while (!stack2.isEmpty()) {
            stringBuilder.append(stack2.pop());
        }
        return stringBuilder.toString();
    }

}
