package main.skill.attributes.condition;

import main.role.Role;
import main.skill.attributes.api.ConditionAttribute;

import java.util.Random;

/**
 * 概率触发
 */
public class ProbabilisticTriggerConditionAttribute implements ConditionAttribute {

    int probability;

    public ProbabilisticTriggerConditionAttribute(int probability) {
        this.probability = probability;
    }

    @Override
    public boolean isValid(Integer value, Role role) {
        int i = new Random().nextInt(100);
        return i <= probability;
    }
}
