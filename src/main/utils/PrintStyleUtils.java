package main.utils;

import main.common.AsciiColor;

/**
 * 打印样式的实用程序类
 */
public class PrintStyleUtils {

    /**
     * Prints a message with a specified color.
     *
     * @param msg   The message to be printed.
     * @param color The color to be used for the message.
     */
    public static void colour(String msg, AsciiColor color) {
        System.out.println(color + msg + AsciiColor.RESET);
    }

    /**
     * Prints a message in red color.
     *
     * @param msg The message to be printed.
     */
    public static void colourRed(String msg) {
        System.out.println(AsciiColor.RED + msg + AsciiColor.RESET);
    }

    // Private constructor to prevent instantiation of utility class
    private PrintStyleUtils() {
    }

    /**
     * Prints a string, wrapping it to a specified maximum line length.
     *
     * @param text          The text to be printed.
     * @param maxLineLength The maximum length of a line.
     */
    public static void wrappedString(String text, int maxLineLength) {
        // Loop through the string
        for (int i = 0; i < text.length(); i += maxLineLength) {
            // Calculate the end index of the substring
            int endIndex = Math.min(i + maxLineLength, text.length());

            // Extract the substring and print it
            alignCenter(text.substring(i, endIndex), maxLineLength);
        }
    }

    /**
     * Prints a string, center-aligned within a specified width.
     *
     * @param text  The text to be printed.
     * @param width The width within which the text should be centered.
     */
    public static void alignCenter(String text, int width) {
        // Generate padding
        StringBuilder paddingBuilder = new StringBuilder();
        for (int i = 0; i < (width - text.length()) / 2; i++) {
            paddingBuilder.append(" ");
        }
        String padding = paddingBuilder.toString();
        System.out.println(padding + text + padding);
    }
}