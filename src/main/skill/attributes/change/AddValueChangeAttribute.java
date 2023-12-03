package main.skill.attributes.change;

import main.dice.Dice;
import main.skill.attributes.api.DiceChangeAttribute;
import main.skill.attributes.api.DiceRetentable;

public class AddValueChangeAttribute implements DiceChangeAttribute, DiceRetentable {

    int add;
    final int MAX_DOT_VALUE = 6;

    public AddValueChangeAttribute(int add) {
        this.add = add;
    }

    @Override
    public void doChange(Dice[] dice, int changeIndex) {
        Integer newValue = dice[changeIndex].getValue() + add;
        if (newValue <= MAX_DOT_VALUE) {
            dice[changeIndex] = Dice.values[newValue];
        }
    }
}
