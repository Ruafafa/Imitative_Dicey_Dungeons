package main.role;


import main.common.AsciiColor;
import main.common.LineStyle;
import main.dice.Dice;
import main.skill.ExclusiveSkillCardTemplates;
import main.skill.SkillCard;
import main.skill.SkillCardTemplates;
import main.utils.PrintStyleUtils;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 这是 Role 类。
 * 它代表游戏中的角色，具有名称、生命值 （hp）、骰子和技能卡等属性。
 * 每个角色都有一个最大生命值 （MAX_HP）、一个骰子袋 （dices） 和一个技能卡袋 （skillCards）。
 * 该类还包括显示骰子、显示技能卡、渲染信息、刷新骰子袋、清除骰子袋、刷新技能卡袋、选择技能卡、获取和设置生命值、
 * 获得最大生命值、获取骰子、获取技能卡、显示属性、获取和设置使用的骰子计数以及重写 toString 方法的方法。
 */
public class Role {

    /**
     * 角色名
     */
    private final String name;

    /**
     * 角色最大生命值
     */
    private int MAX_HP;

    /**
     * 角色生命值
     */
    private Integer hp;

    /**
     * 骰子背包
     */
    private final Dice[] dices;

    /**
     * 默认骰子数量
     */
    private final int DEFAULT_DICE_NUM = 4;

    /**
     * 最大骰子数量
     */
    private final int MAX_DICE_NUM = 8;

    /**
     * 技能（祝福）背包
     */
    private final SkillCard[] skillCards;

    /**
     * 最大技能（祝福）数量
     */
    private final int MAX_SKILL_CARD_NUM = 5;

    /**
     * 生涯战斗使用骰子计数器
     */
    private Integer usedDiceCnt = 0;

    /**
     * 随机生成器
     */
    private final Random random = new Random();



    public Role(String name, Integer hp) {
        this.name = name;
        this.hp = hp;
        this.MAX_HP = hp;
        // 骰子最高上限为 8
        dices = new Dice[MAX_DICE_NUM];
        // 技能（祝福）最高上限为 4
        skillCards = new SkillCard[MAX_SKILL_CARD_NUM];
    }

    /**
     * 获取 Dices
     */
    public void showDices() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("▣ 骰子 >>");
        Thread.sleep(1000);
        for (Dice i : dices) {
            if (i != null) {
                System.out.print("[" + i + "] ");
            }
        }
        System.out.println();
    }

    /**
     * 获取 SkillCardTemplates
     */
    public void showSkillCards() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("▣ 祝福 >>");
        Thread.sleep(1000);
        for (int i = 0; i < skillCards.length; ++i) {
            System.out.println("(" + (i + 1) + ")");
            skillCards[i].renderInfo();
            System.out.println();
        }
    }

    /**
     * 渲染信息
     */
    public void renderInfo() throws InterruptedException {
        System.out.println(LineStyle.LONG_DIVIDE_LINE);
        showAttribute();
        Thread.sleep(1000);
        System.out.println(LineStyle.LONG_DIVIDE_LINE);
        showDices();
        Thread.sleep(1000);
        System.out.println(LineStyle.LONG_DIVIDE_LINE);
        showSkillCards();
        Thread.sleep(1000);
        System.out.println(LineStyle.LONG_DIVIDE_LINE);
    }

    /**
     * 刷新骰子
     */
    public void refreshDiceBag() {
        clearDiceBag();
        for (int i = 0; i < DEFAULT_DICE_NUM; ++i) {
            int dot = new Random().nextInt(6) + 1;
            dices[i] = Dice.values[dot];
        }
    }

    /**
     * 清除骰子
     */
    public void clearDiceBag() {
        Arrays.fill(dices, null);
    }

    /**
     * 刷新祝福
     */
    public void refreshSkillCardBag() {
        SkillCardTemplates[] cardTemplates = SkillCardTemplates.values();
        // 普通祝福
        for (int i = 0; i < MAX_SKILL_CARD_NUM - 1; ++i) {
            if (skillCards[i] == null || skillCards[i].getAvailableCounter() == 0) {
                int index = random.nextInt(cardTemplates.length);
                skillCards[i] = cardTemplates[index].getCard().getCopy();
            }
        }
        // 专属祝福
        ExclusiveSkillCardTemplates[] exclusiveCardTemplates = ExclusiveSkillCardTemplates.values();
        for (ExclusiveSkillCardTemplates cardTemplate : exclusiveCardTemplates) {
            if (name.equals(cardTemplate.getBelong())) {
                skillCards[MAX_SKILL_CARD_NUM - 1] = cardTemplate.getCard().getCopy();
            }
        }
    }

    /**
     * 选择祝福
     * @return 被选择的祝福卡
     */
    public SkillCard chooseSkillCard() throws InterruptedException {
        System.out.print(AsciiColor.GREEN + "【YOU】* 请选择祝福（输入 /skip 结束回合）: ");

        // 选择技能卡
        SkillCard skillCard;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                int index;
                // 跳过选择
                if ("/skip".equals(input)) {
                    clearDiceBag();
                    return null;
                }
                index = Integer.valueOf(input) - 1;
                // 检验索引是否超出选择范围
                if (index < 0 || index >= MAX_SKILL_CARD_NUM) {
                    PrintStyleUtils.colour("【YOU】x 请输入正确的序号\n:", AsciiColor.RED);
                    Thread.sleep(1000);
                    continue;
                }
                skillCard = skillCards[index];
                // 检验所选本回合是否可用
                Integer useCnt = skillCard.getAvailableCounter();
                if (useCnt != null && useCnt.intValue() <= 0) {
                    PrintStyleUtils.colourRed("【YOU】x 使用次数耗尽，请重新选择\n:");
                    Thread.sleep(1000);
                    continue;
                }
                break;
            } catch(NumberFormatException e){
                System.out.print(AsciiColor.RED + "【YOU】x 请输入数字!\n:" + AsciiColor.RESET);
            }
        }
        // 返回所选技能卡
        return skillCard;
    }

    /**
     * 获取 HP
     * @return
     */
    public Integer getHp() {
        return hp;
    }

    /**
     * 更新 HP
     * @param hp
     */
    public void setHp(Integer hp) {
        this.hp = hp;
    }

    /**
     * 获取最大生命值
     * @return
     */
    public int getMaxHp() {
        return MAX_HP;
    }

    /**
     * 获取骰子背包
     * @return
     */
    public Dice[] getDices() {
        return dices;
    }

    /**
     * 获取祝福背包
     * @return
     */
    public SkillCard[] getSkillCards() {
        return skillCards;
    }

    /**
     * 获取属性
     */
    public void showAttribute() {
        System.out.println(name + " HP:" + hp);
    }

    /**
     * 获取骰子使用数
     * @return
     */
    public Integer getUsedDiceCnt() {
        return usedDiceCnt;
    }

    /**
     * 设置骰子使用数
     * @param usedDiceCnt
     */
    public void setUsedDiceCnt(Integer usedDiceCnt) {
        this.usedDiceCnt = usedDiceCnt;
    }

    @Override
    public String toString() {
        return  name + " HP:" + hp;
    }
}
