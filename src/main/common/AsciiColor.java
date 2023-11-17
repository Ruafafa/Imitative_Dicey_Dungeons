package main.common;

public enum AsciiColor {
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    CARMINE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),

    RESET("\u001B[0m"),
    ;
    private final String asciiColor;
    AsciiColor(String asciiColor) {
        this.asciiColor = asciiColor;
    }

    @Override
    public String toString() {
        return asciiColor;
    }
}