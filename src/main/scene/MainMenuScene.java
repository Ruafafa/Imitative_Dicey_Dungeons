package main.scene;

import main.DiceyDungeonsGame;
import main.common.AsciiColor;
import main.common.LineStyle;
import main.utils.PrintStyleUtils;

/**
 * 这是 MainMenuScene 类。
 * 它扩展了 AbstractGameScene 类，并表示游戏的主菜单场景。
 * 该类包括游戏是否正在进行的属性 （isGaming）
 * 它还包括用于预处理、呈现用户界面和更新游戏逻辑的方法。
 */
public class MainMenuScene extends AbstractGameScene {

    /**
     * 是否有游戏启动
     */
    Boolean isGaming = false;

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
    public void renderUi() {

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
            DiceyDungeonsGame.setNextGameScene(new ChooseRoleScene());
        } else if ("/exit".equals(userInput)) {
            System.exit(0);
        } else {
            PrintStyleUtils.colour("【YOU】x 无效输入！\n\n", AsciiColor.RED);
        }
    }

}
