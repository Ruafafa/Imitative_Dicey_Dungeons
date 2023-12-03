package main.common;

public final class LineStyle {

    /**
    * 常量
     */
    public static final LineStyle H1;
    public static final LineStyle H2;
    public static final LineStyle H3;
    public static final LineStyle H4;
    public static final LineStyle LONG_DIVIDE_LINE;


    static {
        H1 = new LineStyle("■");
        H2 = new LineStyle("■");
        H3 = new LineStyle("■");
        H4 = new LineStyle("■");
        LONG_DIVIDE_LINE = new LineStyle("■");
    }

    private final String style;

    LineStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return style;
    }
}
