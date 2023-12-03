package main.common;

public final class AsciiColor {
    public static final AsciiColor BLACK;
    public static final AsciiColor RED;
    public static final AsciiColor GREEN;
    public static final AsciiColor YELLOW;
    public static final AsciiColor BLUE;
    public static final AsciiColor CARMINE;
    public static final AsciiColor CYAN;
    public static final AsciiColor WHITE;
    public static final AsciiColor RESET;

    static {
        BLACK = new AsciiColor("\u001B[30m");
        RED = new AsciiColor("\u001B[31m");
        GREEN = new AsciiColor("\u001B[32m");
        YELLOW = new AsciiColor("\u001B[33m");
        BLUE = new AsciiColor("\u001B[34m");
        CARMINE = new AsciiColor("\u001B[35m");
        CYAN = new AsciiColor("\u001B[36m");
        WHITE = new AsciiColor("\u001B[37m");
        RESET = new AsciiColor("\u001B[0m");
    }

    private final String asciiColor;
    AsciiColor(String asciiColor) {
        this.asciiColor = asciiColor;
    }

    @Override
    public String toString() {
        return asciiColor;
    }

}
