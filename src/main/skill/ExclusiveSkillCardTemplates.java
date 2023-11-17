package main.skill;

import main.skill.attributes.change.AddDiceChangeAttribute;
import main.skill.attributes.condition.ConsumeHpConditionAttribute;
import main.skill.attributes.condition.ProbabilisticTriggerConditionAttribute;
import main.skill.attributes.damage.MultiplierDamageAttribute;
import main.skill.attributes.health.PercentageHealthAttribute;

/**
 * 角色专属级技能
 */
public enum ExclusiveSkillCardTemplates {

    /**
     * 专属技能
     */
    EXCLUSIVE_ARIS("Aris", new SkillCard.Builder("专属·超新星！", "造成 点数 * 59 伤害， 22%概率触发", "无限制")
            .addDamageAttribute(new MultiplierDamageAttribute(59))
            .addConditionAttribute(new ProbabilisticTriggerConditionAttribute(22))
            .addUseableCnt(1)
            .build()),

    EXCLUSIVE_JIE("杰", new SkillCard.Builder("专属·黑暗之拥", "造成 点数 * 20 的伤害, 消耗自身 10HP ", "无限制")
            .addDamageAttribute(new MultiplierDamageAttribute(20))
            .addConditionAttribute(new ConsumeHpConditionAttribute(10))
            .addUseableCnt(3)
            .build()),

    EXCLUSIVE_YUN_DING("云云", new SkillCard.Builder("专属·三角之力", "造成 点数 *3 点伤害, 恢复 点数*3% 点HP, 获得 1-3点数 * 3 个骰子", "无限制")
            .addDamageAttribute(new MultiplierDamageAttribute(3))
            .addHealthAttribute(new PercentageHealthAttribute(3))
            .addDiceChangeAttribute(new AddDiceChangeAttribute(3,1,3))
            .addUseableCnt(3)
            .build()),
    
    ;

    SkillCard card;
    String belong;

    ExclusiveSkillCardTemplates(String belong, main.skill.SkillCard card) {
        this.card = card;
        this.belong = belong;
    }

    public String getBelong() {
        return belong;
    }

    public SkillCard getCard() {
        return card;
    }
}
