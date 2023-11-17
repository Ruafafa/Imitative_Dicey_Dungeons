package main.skill.attributes.health;

import main.role.Role;
import main.skill.attributes.api.HealthAttribute;

public class PercentageHealthAttribute implements HealthAttribute {

    int precentage;

    public PercentageHealthAttribute(int precentage) {
        this.precentage = precentage;
    }

    @Override
    public void doHealth(int value, Role role) {
        Integer hp = role.getHp();
        int maxHp = role.getMaxHp();
        int fianlHp = hp + (precentage * maxHp * value) / 100;
        role.setHp(Math.min(fianlHp, maxHp));
    }
}
