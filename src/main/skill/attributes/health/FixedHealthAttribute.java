package main.skill.attributes.health;

import main.role.Role;
import main.skill.attributes.api.HealthAttribute;

public class FixedHealthAttribute implements HealthAttribute {
    int add;

    public FixedHealthAttribute(int add) {
        this.add = add;
    }

    @Override
    public void doHealth(int value, Role role) {
        role.setHp(role.getHp() + add > role.getMaxHp() ? role.getMaxHp() : role.getHp() + add);
    }
}
