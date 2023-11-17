package main.scene;

import main.DiceyDungeonsGame;
import main.common.AsciiColor;
import main.common.LineStyle;
import main.enemy.EnemyTemplates;
import main.role.RoleManager;
import main.utils.PrintStyleUtil;

public class ChooseRoleScene extends AbstractGameScene{

    /**
     * 角色管理模块
     */
    RoleManager rm = new RoleManager();

    /**
     * 用户最近选择的角色Index
     */
    Integer chooseIndex;

    @Override
    public void preprocess() {
        // banner
        showBanner("ROLE LIST", LineStyle.H2);
        // 选择角色列表展示
        rm.showRoleList();
        System.out.println("——————————————————");
        System.out.println("【YOU】* 请选择角色:");
    }

    @Override
    public void updateLogic() {
        // 选择角色
        while (true) {
            try {
                Integer chooseIndex = inputAndCheckInteger();
                if (rm.chooseRole(chooseIndex)) break;
                PrintStyleUtil.colour("【YOU】x 请选择正确的序号!", AsciiColor.RED);
            } catch (NumberFormatException e) {
                PrintStyleUtil.colour("【YOU】x 请输入数字!", AsciiColor.RED);
            }
        }
        // 切换入战斗场景
        DiceyDungeonsGame.setNextGameScene(new CombatScene(rm.getCombatRole(), EnemyTemplates.CULTIST.getEnemy()));
    }

    @Override
    public void renderUI() {
        System.out.println("[已选定]:" + rm.getCombatRole());
    }
}
