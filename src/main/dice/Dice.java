package main.dice;

public enum Dice {
    /**
     * 1点
     */
    _1(1),
    /**
     * 2点
     */
    _2(2),
    /**
     * 3点
     */
    _3(3),
    /**
     * 4点
     */
    _4(4),
    /**
     * 5点
     */
    _5(5),
    /**
     * 6点
     */
    _6(6),
    ;

    Integer value;
    Dice(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
