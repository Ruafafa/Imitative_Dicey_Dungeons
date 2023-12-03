package main.enemy;

import main.common.LineStyle;

import java.util.Random;

/**
 * 敌人
 */
public class Enemy {

    /**
     * 敌人名
     */
    private final String name;

    /**
     * 敌人生命值
     */
    private Integer hp;

    /**
     * 最小攻击力
     */
    private final int MIN_ATTACK;

    /**
     * 最大攻击力
     */
    private final int MAX_ATTACK;

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Enemy(String name, Integer hp, int minAttack, int maxAttack) {
        this.name = name;
        this.hp = hp;
        MIN_ATTACK = minAttack;
        MAX_ATTACK = maxAttack;
    }

    /**
     * 渲染信息
     */
    public void renderInfo() throws InterruptedException {
        System.out.println(LineStyle.LONG_DIVIDE_LINE);
        System.out.println(name + " HP:" + hp);
        Thread.sleep(1000);
        System.out.println(LineStyle.LONG_DIVIDE_LINE);
    }

    /**
     * 发起攻击
     */
    public int attack() {
        return new Random().nextInt(MAX_ATTACK - MIN_ATTACK + 1) + MIN_ATTACK;
    }
}
