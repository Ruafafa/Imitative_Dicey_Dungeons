package main.utils;

import main.common.AsciiColor;

public class PrintStyleUtil {

    public static void colour(String msg, AsciiColor color) {
        System.out.println(color + msg + AsciiColor.RESET);
    }

    public static void colourRed(String msg) {
        System.out.println(AsciiColor.RED + msg + AsciiColor.RESET);
    }

    public static void wrappedString(String text, int maxLineLength) {
        // 循环迭代字符串
        for (int i = 0; i < text.length(); i += maxLineLength) {
            // 计算子字符串的结束索引
            int endIndex = Math.min(i + maxLineLength, text.length());

            // 截取子字符串并输出
            alignCenter(text.substring(i, endIndex), maxLineLength);
        }
    }

    public static void alignCenter(String text, int width) {
        // 生成 padding
        StringBuilder paddingBuilder = new StringBuilder();
        for (int i = 0; i < (width - text.length()) / 2; i++)
            paddingBuilder.append(" ");
        String padding = paddingBuilder.toString();
        System.out.println(padding + text + padding);
    }
}
