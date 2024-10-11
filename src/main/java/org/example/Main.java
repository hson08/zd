package org.example;

import java.util.Stack;

public class Main {

    public static String removeConsecutiveChars(String s) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();

        for (char c : s.toCharArray()) {
            // 如果栈不为空且当前字符与栈顶字符相同
            if (!stack.isEmpty() && stack.peek() == c) {
                int count = countStack.pop() + 1; // 增加计数
                countStack.push(count);
                // 如果计数达到 3，弹出栈顶字符
                if (count == 3) {
                    stack.pop(); // 移除字符
                    countStack.pop(); // 移除计数
                }
            } else {
                stack.push(c); // 将当前字符入栈
                countStack.push(1); // 初始化计数为 1
            }
        }

        // 构建结果字符串
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }
        return result.toString();
    }

    public static String processString(String s) {
        StringBuilder result;

        while (true) {
            StringBuilder tempResult = new StringBuilder();
            int i = 0;
            boolean changed = false; // 标记是否有变更

            while (i < s.length()) {
                char currentChar = s.charAt(i);
                int count = 1;

                // 统计当前字符的连续出现次数
                while (i + 1 < s.length() && s.charAt(i + 1) == currentChar) {
                    count++;
                    i++;
                }

                // 根据连续字符的数量进行替换或删除
                if (count == 3) {
                    // 如果超等于 3 个，则用字母顺序前面的字符替换
                    changed = true; // 发生了变更
                    if ('a' == currentChar) {
                        tempResult.append("");
                    } else {
                        char replacementChar = (char) (currentChar - 1);
                        tempResult.append(replacementChar);
                    }
                } else {
                    // 如果是 1 或 2 个相同字符，直接添加到结果中
                    tempResult.append(currentChar);
                }

                i++; // 移动到下一个字符
            }

            // 更新字符串为临时结果
            s = tempResult.toString();
            result = tempResult; // 更新结果
            // 如果没有变更，退出循环
            if (!changed) {
                break;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String input = "aabcccbbad"; // 示例输入
        String output = removeConsecutiveChars(input);
        System.out.println("Result: " + output);

        String input2 = "abcccbad"; // 示例输入
        String output2 = processString(input2);
        System.out.println("Result: " + output2);

    }
}