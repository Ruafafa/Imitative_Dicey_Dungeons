package main.common;

public enum LineStyle {

    /**
    * 常量
     */

    H1("■"),
    H2("="),
    H3("□"),
    H4("——"),
    LONG_DIVIDE_LINE("————————————————————"),
    ;

    private final String style;

    LineStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return style;
    }
}
