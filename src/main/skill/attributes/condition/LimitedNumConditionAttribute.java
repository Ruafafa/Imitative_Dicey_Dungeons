package main.skill.attributes.condition;

import main.role.Role;
import main.skill.attributes.api.ConditionAttribute;

public class LimitedNumConditionAttribute implements ConditionAttribute {
    int maxNum;
    int minNum;

    public LimitedNumConditionAttribute(int minNum, int maxNum) {
        this.maxNum = maxNum;
        this.minNum = minNum;
    }

    @Override
    public boolean isValid(Integer value, Role role) {
        return minNum <= value && value <= maxNum;
    }
}
