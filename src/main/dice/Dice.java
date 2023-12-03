package main.dice;
/**
 * 这是骰子类。它代表一个有六个面的骰子，每个面都有一个从 1 到 6 的值。该类是 final，这意味着它不能被子类化。
 * <p>使用枚举 enum 实现更好</p>
 */
public final class Dice {
    /**
     * 1点
     */
    public static final Dice DOT1;
    /**
     * 2点
     */
    public static final Dice DOT2;
    /**
     * 3点
     */
    public static final Dice DOT3;
    /**
     * 4点
     */
    public static final Dice DOT4;
    /**
     * 5点
     */
    public static final Dice DOT5;
    /**
     * 6点
     */
    public static final Dice DOT6;

    /**
     * 容器
     */
    public static final Dice[] values;



    static {
        DOT1 = new Dice(1);
        DOT2 = new Dice(2);
        DOT3 = new Dice(3);
        DOT4 = new Dice(4);
        DOT5 = new Dice(5);
        DOT6 = new Dice(6);
        // 为了方便遍历 对应索引，将null放在第一个位置
        values = new Dice[]{null, DOT1, DOT2, DOT3, DOT4, DOT5, DOT6};
    }

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
