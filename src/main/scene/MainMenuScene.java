package main.scene;

import main.DiceyDungeonsGame;
import main.common.AsciiColor;
import main.common.LineStyle;
import main.utils.PrintStyleUtil;

/**
 * 主菜单场景
 */
public class MainMenuScene extends AbstractGameScene {

    /**
     * 是否有游戏启动
     */
    Boolean isGaming = false;

    /**
     * 选择角色场景
     */
    AbstractGameScene chooseRoleScene = new ChooseRoleScene();

    @Override
    public void preprocess() {
        if (!isGaming) {
            // 没有游戏进程
            showBanner("MAIN MENU", LineStyle.H2);
            System.out.println("[1] 输入 /start 开始游戏");
            System.out.println("[2] 输入 /exit 退出游戏");
        }
    }

    @Override
    public void renderUI() {

    }

    @Override
    public void updateLogic() {
        // 获取输入
        userInput = getUserInput();
        // 修改跳转逻辑
        if ("/start".equals(userInput)) {
            // 游戏启动
            isGaming = true;
            // 游戏启动-> 显示角色选择画面
            DiceyDungeonsGame.setNextGameScene(chooseRoleScene);
        } else if ("/exit".equals(userInput)) {
            System.exit(0);
        } else {
            PrintStyleUtil.colour("【YOU】x 输入错误！\n\n", AsciiColor.RED);
        }
    }

}
