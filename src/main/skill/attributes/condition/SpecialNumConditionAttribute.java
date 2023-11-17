package main.skill.attributes.condition;

import main.role.Role;
import main.skill.attributes.api.ConditionAttribute;

/**
 * 奇数、偶数骰子条件校验
 */
public class SpecialNumConditionAttribute implements ConditionAttribute {

    Integer specialNum;

    public SpecialNumConditionAttribute(Integer num) {
        this.specialNum = num;
    }

    @Override
    public boolean isValid(Integer value, Role role) {
        if (specialNum % 2 == 0)
            return value % 2 == 0;
        return value % 2 != 0;
    }
}
