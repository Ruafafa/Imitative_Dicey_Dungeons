package main.skill.attributes.api;

import main.dice.Dice;

public interface DiceChangeAttribute {
    void doChange(Dice[] dice, int changeIndex);
}
