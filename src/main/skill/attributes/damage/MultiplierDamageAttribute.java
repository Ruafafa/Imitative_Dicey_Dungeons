package main.skill.attributes.damage;

import main.enemy.Enemy;
import main.skill.attributes.api.DamageAttribute;

/**
 * 倍率伤害属性
 */
public class MultiplierDamageAttribute implements DamageAttribute {
    /**
     * 点子数 * 倍率
     */
    int Multiplier;

    public MultiplierDamageAttribute(int multiplier) {
        Multiplier = multiplier;
    }

    @Override
    public void doAttack(Integer num, Enemy enemy) {
        Integer hp = enemy.getHp();
        enemy.setHp(hp - num * Multiplier);
    }
}
