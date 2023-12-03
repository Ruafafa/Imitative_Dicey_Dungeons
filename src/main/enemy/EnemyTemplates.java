package main.enemy;
/**
 * 表示敌方模板的类。此类是最终类，不能子类化。
 */
public final class EnemyTemplates {

    public static final EnemyTemplates ZAKU;
    public static final EnemyTemplates CULTIST;


    static {
        ZAKU = new EnemyTemplates(0, new Enemy("杂鱼", 100, 10, 20));
        CULTIST = new EnemyTemplates(1, new Enemy("腐化之心", 999, 1, 50));
    }

    Enemy enemy;
    Integer index;

    EnemyTemplates(Integer index, Enemy enemy) {
        this.enemy = enemy;
        this.index = index;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
