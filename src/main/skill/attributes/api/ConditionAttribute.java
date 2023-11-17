package main.skill.attributes.api;

import main.role.Role;

public interface ConditionAttribute {

    boolean isValid(Integer value, Role role);
}
