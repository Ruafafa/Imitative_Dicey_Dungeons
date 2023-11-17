package main.skill.attributes.change;

import main.dice.Dice;
import main.skill.attributes.api.DiceChangeAttribute;

public class AddValueChangeAttribute implements DiceChangeAttribute {

    int add;

    public AddValueChangeAttribute(int add) {
        this.add = add;
    }

    @Override
    public void doChange(Dice[] dice, int changeIndex) {
        Integer newValue = dice[changeIndex].getValue() + add;
        if (newValue <= 6) {
            dice[changeIndex] = Dice.valueOf("_" + newValue);
        }
    }
}
