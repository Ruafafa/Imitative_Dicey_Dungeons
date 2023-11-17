package main.skill.attributes.damage;

import main.enemy.Enemy;
import main.skill.attributes.api.DamageAttribute;

public class FixedPlusDamageAttribute implements DamageAttribute {

    int fixedDamage;
    int plusDamage;

    public FixedPlusDamageAttribute(int fixedDamage, int plusDamage) {
        this.fixedDamage = fixedDamage;
        this.plusDamage = plusDamage;
    }

    @Override
    public void doAttack(Integer num, Enemy enemy) {
        Integer hp = enemy.getHp();
        enemy.setHp(hp - (fixedDamage + plusDamage * num));
    }
}
