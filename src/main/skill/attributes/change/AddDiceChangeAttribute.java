package main.skill.attributes.change;

import main.dice.Dice;
import main.skill.attributes.api.DiceChangeAttribute;

import java.util.Random;

public class AddDiceChangeAttribute implements DiceChangeAttribute {

    int addNum;
    int minValue;
    int maxValue;

    public AddDiceChangeAttribute(int addNum, int minValue, int maxValue) {
        this.addNum = addNum;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public void doChange(Dice[] changedDices, int changeIndex) {
        int cnt = addNum;
        for (int i = 0; i < changedDices.length; i++) {
            Dice[] dices = Dice.values();
            if (cnt == 0) break;
            // 补充空骰子
            if (changedDices[i] == null) {
                changedDices[i] = dices[new Random().nextInt(maxValue - minValue + 1)  + minValue - 1];
                cnt--;
            }
        }
    }
}
