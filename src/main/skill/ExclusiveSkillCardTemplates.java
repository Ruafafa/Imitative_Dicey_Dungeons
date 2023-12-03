package main.skill;

import main.skill.attributes.change.AddDiceChangeAttribute;
import main.skill.attributes.condition.ConsumeHpConditionAttribute;
import main.skill.attributes.condition.ProbabilisticTriggerConditionAttribute;
import main.skill.attributes.damage.MultiplierDamageAttribute;
import main.skill.attributes.health.PercentageHealthAttribute;

/**
 * <h1>角色专属级技能</h1>
 * 这个枚举代表游戏中不同角色的专属技能卡。
 * 每张技能卡都有一个它所属的角色和一个与之相关的技能卡对象。
 * SkillCard对象是使用Builder模式构建的。
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/Enum.html">Enum API</a>
 * @see <a href="https://www.runoob.com/cprogramming/c-enum.html">Enum 菜鸟教程</a>
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
