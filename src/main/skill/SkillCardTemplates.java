package main.skill;

import main.role.Role;
import main.skill.attributes.change.AddDiceChangeAttribute;
import main.skill.attributes.change.AddValueChangeAttribute;
import main.skill.attributes.condition.ConsumeHpConditionAttribute;
import main.skill.attributes.condition.LimitedNumConditionAttribute;
import main.skill.attributes.condition.ProbabilisticTriggerConditionAttribute;
import main.skill.attributes.condition.SpecialNumConditionAttribute;
import main.skill.attributes.damage.FixedPlusDamageAttribute;
import main.skill.attributes.damage.MultiplierDamageAttribute;
import main.skill.attributes.health.PercentageHealthAttribute;


/**
*这个枚举代表游戏中不同类型的技能卡。
*每个技能卡都有一个类别和一个与之相关的SkillCard对象。
* SkillCard对象是使用Builder模式构建的。
*/
public enum SkillCardTemplates {

    /**
     * 攻击类
     */
    DAMAGE_PERFECT_STRIKE("damage", new SkillCard.Builder("完美打击", "造成点数 * 12 伤害", "投入一个奇数骰子")
            .addDamageAttribute(new MultiplierDamageAttribute(12))
            .addConditionAttribute(new SpecialNumConditionAttribute(1))
            .addUseableCnt(1)
            .build()),

    DAMAGE_ANGRY("damage", new SkillCard.Builder("愤怒", "造成 2 伤害，本场战斗每使用一个骰子，该技能伤害 + 3", "无限制")
            .addDamageAttribute(new FixedPlusDamageAttribute(2, 3))
            .addUseableCnt(3)
            .build()),

    DAMAGE_BOOM("damage", new SkillCard.Builder("#跟你爆了!", "造成 点数 * 99 伤害，消耗 99 HP", "投入一个偶数骰子")
            .addDamageAttribute(new MultiplierDamageAttribute(99))
            .addConditionAttribute((value, role) -> {
                if (new SpecialNumConditionAttribute(2).isValid(value, role)) {
                    new ConsumeHpConditionAttribute(99).isValid(value, role);
                    return true;
                }
                return false;
            })
            .addUseableCnt(1)
            .build()),


    /**
     * 变化类
     */
    CHANGE_THE_ITALIAN_JOB("change", new SkillCard.Builder("骰子·偷天换日", "获得一个 4-5 点的骰子", "最大 2 点")
            .addDiceChangeAttribute(new AddDiceChangeAttribute(1, 4, 5))
            .addConditionAttribute(new LimitedNumConditionAttribute(1, 2))
            .addUseableCnt(2)
            .build()),

    CHANGE_GIRLISH_PRIVILEGE("change", new SkillCard.Builder("递归", "投入骰子点数 + 1", "最大 5 点")
            .addDiceChangeAttribute(new AddValueChangeAttribute(1))
            .addConditionAttribute(new LimitedNumConditionAttribute(1, 5))
            .addUseableCnt(2)
            .build()),

    CHANGE_SUPER_LOTTO("change", new SkillCard.Builder("骰子大乐透", "获得随机点数的骰子，小概率失去 9 HP", "无限制")
            .addDiceChangeAttribute(new AddDiceChangeAttribute(1, 1, 6))
            .addConditionAttribute((Integer value, Role role) -> {
                        if (new ProbabilisticTriggerConditionAttribute(33).isValid(value, role)) {
                            new ConsumeHpConditionAttribute(9).isValid(value, role);
                        }
                        return true;
                    }
            )
            .addDamageAttribute((num, enemy) -> System.out.println("【YOU】x 你失去了 9 HP"))
            .addUseableCnt(3)
            .build()),

    /**
     * 回复类
     */
    HEALTH_TAKO_WASABI("health", new SkillCard.Builder("回复·芥末章鱼", "回复 点数*1% 生命值，并造成 点数*5 伤害", "无限制")
            .addHealthAttribute(new PercentageHealthAttribute(1))
            .addDamageAttribute(new MultiplierDamageAttribute(5))
            .addUseableCnt(2)
            .build()),

    HEALTH_BIG_LUEN("health", new SkillCard.Builder("速效就补丸", "回复满HP", "最小 6")
            .addHealthAttribute(new PercentageHealthAttribute(100))
            .addConditionAttribute(new LimitedNumConditionAttribute(6, 6))
            .addUseableCnt(1)
            .build()),

    ;


    SkillCard card;
    String category;

    SkillCardTemplates(String category, main.skill.SkillCard card) {
        this.card = card;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public SkillCard getCard() {
        return card;
    }
}
