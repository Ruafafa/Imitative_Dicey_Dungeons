package main.skill;

import main.skill.attributes.api.ConditionAttribute;
import main.skill.attributes.api.DamageAttribute;
import main.skill.attributes.api.DiceChangeAttribute;
import main.skill.attributes.api.HealthAttribute;
import main.utils.PrintStyleUtils;

/**
* 这个类代表游戏中的技能卡。
* 每个技能卡都有名称、描述、条件和各种属性。
* SkillCard对象是使用Builder模式构建的。
*/
public class SkillCard {
    /**
     * 技能名
     */
    protected String name;

    /**
     * 技能描述
     */
    protected String description;

    /**
     * 条件描述
     */
    protected String condition;
    /**
     * 条件属性
     */
    protected ConditionAttribute conditionAttribute;

    /**
     * 技能属性
     * - DamageAttribute 伤害
     * - DiceChangeAttribute 骰子变化
     * - HealthAttribute 生命回复
     */
    protected DamageAttribute damageAttribute;
    protected DiceChangeAttribute diceChangeAttribute;
    protected HealthAttribute healthAttribute;

    /**
     * 可用计数器
     */
    protected Integer availableCounter;

    /**
     * 卡牌本身不会改变，该类的情况允许使用-浅拷贝 深拷贝
     * @apiNote 对于 useableCnt, 拷贝后互不影响,因为重新设置引用
     * @return
     */
    public SkillCard getCopy() {
        return new SkillCard(this.name, this.description, this.condition, this.conditionAttribute, this.damageAttribute,
                this.diceChangeAttribute, this.healthAttribute, this.availableCounter);
    }

    /**
     * 技能祝福渲染
     */
    public void renderInfo() {
        System.out.println("[------------------]");
        PrintStyleUtils.alignCenter(name, 18);
        PrintStyleUtils.wrappedString("[祝福简述] " + description, 12);
        PrintStyleUtils.alignCenter("[点数限制] " + condition,18);
        if (availableCounter != null) {
            PrintStyleUtils.alignCenter("[剩余可用]" + availableCounter,18);
        }
        System.out.println("[------------------]");
    }

    public void setAvailableCounter(Integer availableCounter) {
        this.availableCounter = availableCounter;
    }

    /**
     * 本回合使用次数
     * @return
     */
    public Integer getAvailableCounter() {
        return availableCounter;
    }

    public ConditionAttribute getConditionAttribute() {
        return conditionAttribute;
    }

    public DamageAttribute getDamageAttribute() {
        return damageAttribute;
    }

    public DiceChangeAttribute getDiceChangeAttribute() {
        return diceChangeAttribute;
    }

    public HealthAttribute getHealthAttribute() {
        return healthAttribute;
    }


    private SkillCard(String name, String description, String condition, ConditionAttribute conditionAttribute, DamageAttribute damageAttribute, DiceChangeAttribute diceChangeAttribute, HealthAttribute healthAttribute, Integer availableCounter) {
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.conditionAttribute = conditionAttribute;
        this.damageAttribute = damageAttribute;
        this.diceChangeAttribute = diceChangeAttribute;
        this.healthAttribute = healthAttribute;
        this.availableCounter = availableCounter;
    }

    private SkillCard(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.condition = builder.condition;
        this.damageAttribute = builder.damageAttribute;
        this.diceChangeAttribute = builder.diceChangeAttribute;
        this.healthAttribute = builder.healthAttribute;
        this.conditionAttribute = builder.conditionAttribute;
        this.availableCounter = builder.usedCnt;
    }

    /**
     * 静态内部类，作为建造者
     */
    public static class Builder {
        /**
         * 技能名
         */
        private String name;

        /**
         * 技能描述
         */
        private String description;

        /**
         * 条件描述
         */
        private String condition;

        /**
         * 发动条件属性
         */
        private ConditionAttribute conditionAttribute;

        /**
         * 技能属性
         * - DamageAttribute 伤害
         * - DiceChangeAttribute 骰子变化
         * - HealthAttribute 生命回复
         */
        private DamageAttribute damageAttribute;
        private DiceChangeAttribute diceChangeAttribute;
        private HealthAttribute healthAttribute;
        /**
         * 使用计数器
         */
        private Integer usedCnt;


        public Builder(String name, String description, String condition) {
            this.name = name;
            this.description = description;
            this.condition = condition;
        }

        public Builder addDamageAttribute(DamageAttribute damageAttribute) {
            this.damageAttribute = damageAttribute;
            return this;
        }

        public Builder addDiceChangeAttribute(DiceChangeAttribute diceChangeAttribute) {
            this.diceChangeAttribute = diceChangeAttribute;
            return this;
        }

        public Builder addHealthAttribute(HealthAttribute healthAttribute) {
            this.healthAttribute = healthAttribute;
            return this;
        }

        public Builder addConditionAttribute(ConditionAttribute conditionAttribute) {
            this.conditionAttribute = conditionAttribute;
            return this;
        }

        public Builder addUseableCnt(int cnt) {
            usedCnt = cnt;
            return this;
        }

        public SkillCard build() {
            // 创建卡牌对象
            return new SkillCard(this);
        }
    }

}
