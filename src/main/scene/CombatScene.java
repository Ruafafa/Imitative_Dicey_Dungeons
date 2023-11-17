package main.scene;

import main.DiceyDungeonsGame;
import main.common.AsciiColor;
import main.common.LineStyle;
import main.dice.Dice;
import main.enemy.Enemy;
import main.role.Role;
import main.skill.SkillCard;
import main.skill.attributes.api.ConditionAttribute;
import main.skill.attributes.api.DamageAttribute;
import main.skill.attributes.api.DiceChangeAttribute;
import main.skill.attributes.api.HealthAttribute;
import main.skill.attributes.condition.ProbabilisticTriggerConditionAttribute;
import main.skill.attributes.damage.FixedPlusDamageAttribute;
import main.utils.PrintStyleUtil;

/**
 * <h1>开启一场战斗场景</h1>
 * @apiNote  独立，不作为方法提供者
 */
public class CombatScene extends AbstractGameScene{

    /**
     * 当前战斗玩家
     */
    private final Role combatPlayer;
    /**
     * 当前战斗敌人
     */
    private final Enemy combatEnemy;

    /**
     * 当前战斗中玩家骰子背包
     */
    private final Dice[] combatDiceBag;
    /**
     * 回合计数器
     */
    private int roundCnt = 0;
    /**
     * 骰子使用计数器
     */
    private int usedDiceCnt = 0;

    /**
     * 战斗场景初始化
     * @param combatPlayer （必传）
     * @param combatEnemy（必传）
     */
    public CombatScene(Role combatPlayer, Enemy combatEnemy) {
        this.combatPlayer = combatPlayer;
        this.combatEnemy = combatEnemy;
        this.combatDiceBag = combatPlayer.getDices();
    }

    @Override
    public void preprocess() throws InterruptedException, CloneNotSupportedException {
        // 回合开始玩家重置骰子、技能
        combatPlayer.refreshSkillCardBag();
        combatPlayer.refreshDiceBag();
        // 0 回合展示
        if (roundCnt == 0) {
            showBanner("COMBAT START", LineStyle.H2);
            roundCnt++;
        }
        // 战斗回合信息
        renderCombat();
    }

    @Override
    public void updateLogic() throws InterruptedException {
        playerTurn();
        enemyTurn();
        // 检测是否结束战斗
        if (operateSummaryAndJudgeEnd()) {
            // 提示结束
            System.out.println(AsciiColor.RED + "");
            showBanner("GAME OVER!", LineStyle.H1);
            System.out.println(AsciiColor.RESET + "");
            Thread.sleep(2000);
            // 返回主页面
            DiceyDungeonsGame.resetScene();
        }
        roundCnt++;
    }

    @Override
    public void renderUI() {
        showBanner("回合结束", LineStyle.H2);
    }

    /**
     * 渲染玩家、敌人信息
     * @throws InterruptedException
     */
    private void renderCombat() throws InterruptedException {
        // 玩家状态展示 （蓝色）
        System.out.println(AsciiColor.BLUE);
        showBanner("你的状态", LineStyle.H3);
        combatPlayer.renderInfo();
        // 敌人状态展示 （洋红色）
        System.out.println(AsciiColor.RESET + " " + AsciiColor.CARMINE);
        showBanner("敌人状态", LineStyle.H3);
        combatEnemy.renderInfo();
    }


    /**
     * 玩家回合操作
     * -结束条件：骰子用尽或主动结束回合
     * @throws InterruptedException
     */
    public void playerTurn() throws InterruptedException {
        // 玩家回合内（绿色）
        System.out.println(AsciiColor.GREEN + " ");

        showBanner("我方行动 Round" + roundCnt, LineStyle.H2);
        Thread.sleep(1000);
        // 玩家回合内。无骰子、主动结束 ——>循环退出
        while (!operateSummaryAndJudgeEnd()) {
            // 展示玩家的祝福、骰子
            System.out.println(AsciiColor.GREEN + "");
            combatPlayer.showSkillCards();
            combatPlayer.showDices();
            // 引导玩家选择祝福
            SkillCard skillCard = combatPlayer.chooseSkillCard();
            // skip选择，循环退出
            if (skillCard == null) break;
            // 选择成功，对玩家给出的祝福进行展示
            System.out.println("" + AsciiColor.YELLOW);
            showBanner("YOU CHOICE", LineStyle.H4);
            skillCard.renderInfo();
            combatPlayer.showDices();
            // 为当前祝福选择骰子
            if (!useSkillCard(skillCard)) continue;
            // 展示敌我状态
            System.out.println(AsciiColor.CARMINE + "");
            System.out.println("【YOU】* 发动了祝福！");
            combatEnemy.renderInfo();
            System.out.println(AsciiColor.BLUE + combatPlayer.toString());
            Thread.sleep(1000);
            // 检查骰子是否用完
            if (combatDiceBag[0] == null) break;
        }

        System.out.println(AsciiColor.RESET + " ");
    }



    /**
     * 技能卡选定、投入骰子界面
     * @param usedSkillCard
     * @return 技能是否被成功使用
     * @throws InterruptedException
     */
    public boolean useSkillCard(SkillCard usedSkillCard) throws InterruptedException {
        // 获取骰子
        System.out.print("【YOU】* 请输入要使用的骰子序号（从1开始）:");
        int diceIndex = inputAndCheckInteger() - 1;
        Dice dice = combatDiceBag[diceIndex];
        // 解析技能卡
        ConditionAttribute condition = usedSkillCard.getConditionAttribute();
        DamageAttribute damage = usedSkillCard.getDamageAttribute();
        DiceChangeAttribute diceChange = usedSkillCard.getDiceChangeAttribute();
        HealthAttribute health = usedSkillCard.getHealthAttribute();
        // 骰子可用性校验
        if (dice == null) {
            PrintStyleUtil.colourRed("【YOU】x 请选择正确的骰子序号");
            Thread.sleep(1000);
            return false;
        }
        int diceValue = dice.getValue();
        // 技能启动条件检测
        if (checkConditionAttr(condition, diceValue, diceIndex, usedSkillCard)) return false;
        // 伤害属性检测
        checkDamageAttr(damage, diceValue);
        // 变化属性检测
        checkDiceChangeAttr(diceChange, combatDiceBag, diceIndex);
        // 回复属性检测
        checkHealthAttr(health, diceValue);
        consumeSkillCardAndDice(usedSkillCard, diceIndex);
        // 返回使用成功、下一步
        return true;
    }

    /**
     * 检验回复属性并使用
     * @param health
     */
    private void checkHealthAttr(HealthAttribute health, int value) {
        if (health != null) {
            // 使用骰子
            health.doHealth(value, combatPlayer);
        }
    }

    /**
     * 检验变化属性并使用
     * @param diceChange
     * @param changedDices
     */
    private void checkDiceChangeAttr(DiceChangeAttribute diceChange, Dice[] changedDices, int changeIndex) {
        if (diceChange != null) {
            // 使用骰子
            diceChange.doChange(changedDices, changeIndex);
        }
    }

    /**
     * 检验伤害属性并使用
     * @param damage
     * @param diceValue
     */
    private void checkDamageAttr(DamageAttribute damage, int diceValue) {
        if (damage != null) {
            // 使用骰子
            if (damage instanceof FixedPlusDamageAttribute) {
                damage.doAttack(usedDiceCnt, combatEnemy);
            } else {
                damage.doAttack(diceValue, combatEnemy);
            }
        }
    }

    /**
     * 检验启动条件属性并使用
     * @param condition
     * @param diceValue
     * @return true 被限制
     * @throws InterruptedException
     */
    private boolean checkConditionAttr(ConditionAttribute condition, int diceValue, int diceIndex, SkillCard usedSkillCard) throws InterruptedException {
        if (condition != null) {
            boolean valid = condition.isValid(diceValue, combatPlayer);
            if (!valid) {
                // 概率触发
                if (condition instanceof ProbabilisticTriggerConditionAttribute) {
                    consumeSkillCardAndDice(usedSkillCard, diceIndex);
                    PrintStyleUtil.colourRed("【YOU】x 触发失败(|w|)");
                } else {
                    PrintStyleUtil.colourRed("【YOU】x 请注意限制！");
                }
                Thread.sleep(1000);
                return true;
            }
        }
        return false;
    }

    /**
     * 技能使用后状态检测
     * @return true 回合结束
     */
    public boolean operateSummaryAndJudgeEnd() {
        // 检查敌人是不是似了
        boolean isEnemyDie = combatEnemy.getHp() <= 0;
        // 检查自己
        boolean isPlayerAlive = combatPlayer.getHp() <= 0;
        return isEnemyDie || isPlayerAlive;
    }

    /**
     * 消耗技能卡和骰子
     * @param usedSkillCard
     * @param diceIndex
     */
    private void consumeSkillCardAndDice(SkillCard usedSkillCard, int diceIndex) {
        // 使用完毕，技能卡使用次数 - 1
        Integer useableCnt = usedSkillCard.getUseableCnt();
        if (useableCnt != null) {
            usedSkillCard.setUseableCnt(useableCnt - 1);
        }
        // 使用完毕，清除该骰子，玩家骰子使用数 + 1
        combatDiceBag[diceIndex] = null;
        combatPlayer.setUsedDiceCnt(combatPlayer.getUsedDiceCnt() + 1);
        usedDiceCnt++;
        // 整理骰子背包，使得碎片空间左对齐
        clearUpBag(combatDiceBag);
    }

    /**
     * 整理碎片背包、左对齐
     * @apiNote   [1,null,2,3,null,4] -> [1,2,3,4,null,null]
     * @param bag
     */
    public void clearUpBag(Object[] bag) {
        int nonZeroIndex = 0;

        // 找到第一个空格子
        while (nonZeroIndex < bag.length && bag[nonZeroIndex] != null) {
            nonZeroIndex++;
        }
        // 整理
        for (int i = nonZeroIndex + 1; i < bag.length; i++) {
            if (bag[i] != null) {
                bag[nonZeroIndex] = bag[i];
                bag[i] = null;
                nonZeroIndex++;
            }
        }
    }

    /**
     * 敌人回合
     */
    public void enemyTurn() throws InterruptedException {
        System.out.println("" + AsciiColor.CARMINE);
        showBanner("敌方行动 Round" + roundCnt, LineStyle.H2);
        Thread.sleep(1000);
        // 攻击？
        int attack = combatEnemy.attack();
        System.out.println("【\\w/】敌人对你造成了" + attack + "点伤害!");
        combatPlayer.setHp(combatPlayer.getHp() - attack);
    }
}
