package main.enemy;

public enum EnemyTemplates {

    ZAKU(0, new Enemy("杂鱼", 100, 10, 20)),
    CULTIST(2, new Enemy("腐化之心", 999, 1, 50)),



    ;

    Enemy enemy;
    Integer index;

    EnemyTemplates(Integer index, Enemy enemy) {
        this.enemy = enemy;
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
