package main.scene;

import main.DiceyDungeonsGame;
import main.common.AsciiColor;
import main.common.LineStyle;
import main.enemy.EnemyTemplates;
import main.role.RoleManager;
import main.utils.PrintStyleUtils;
/**
 * 这是 ChooseRoleScene 类。
 * 它扩展了 AbstractGameScene 类，并表示用户为游戏选择角色的场景。
 * 该类包括角色管理器 （rm） 和用户所选角色索引 （chooseIndex） 的属性。
 * 它还包括用于预处理场景、更新游戏逻辑和呈现用户界面的方法
 */
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
        // banner 展示
        showBanner("ROLE LIST", LineStyle.H2);
        // 选择角色列表展示
        rm.showRoleList();
    }

    @Override
    public void updateLogic() {
        // 选择角色
        while (!rm.chooseRole(inputAndCheckInteger())) {
            PrintStyleUtils.colour("【YOU】x 请选择正确的序号!", AsciiColor.RED);
        }
        // 切换入战斗场景
        DiceyDungeonsGame.setNextGameScene(new CombatScene(rm.getCombatRole(), EnemyTemplates.CULTIST.getEnemy()));
    }

    @Override
    public void renderUi() {
        System.out.println("[已选定]:" + rm.getCombatRole());
    }
}
