package main.skill.attributes.condition;

import main.role.Role;
import main.skill.attributes.api.ConditionAttribute;

public class ConsumeHpConditionAttribute implements ConditionAttribute {

    int consumevalue = 0;

    public ConsumeHpConditionAttribute(int consumeValue) {
        this.consumevalue = consumeValue;
    }

    @Override
    public boolean isValid(Integer value, Role role) {
        int hp = role.getHp();
        int newHp = hp - consumevalue <= 0 ? 1 : hp - consumevalue;
        role.setHp(newHp);
        return true;
    }
}
