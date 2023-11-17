package main;

import main.scene.AbstractGameScene;
import main.scene.MainMenuScene;

public class DiceyDungeonsGame {

    /**
     * 当前场景
     */
    private static AbstractGameScene currentGameScene;
    /**
     * 下一个场景
     */
    private static AbstractGameScene nextGameScene = null;

    /**
     * 游戏启动类
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException {
        // 游戏初始化
        init();
        // 异常情况处理
        if (currentGameScene == null) {
            System.out.println("游戏引擎异常~");
            System.exit(-1);
        }
        // 游戏流程模板
        while (true) {
            // 预先显示
            currentGameScene.preprocess();
            // 更新数据
            currentGameScene.updateLogic();
            // 渲染画面
            currentGameScene.renderUI();
            // 场景检查
            checkAndSwitchToNext();
        }
    }

    /**
     * 游戏初始化操作
     */
    private static void init() {
        // JDK 15+ 替换为文本块
        System.out.println("■■■■■■■■■■■■■■■■■■■■");
        System.out.println("■  Dicey Dungeons  ■");
        System.out.println("■     --!启动!--    ■");
        System.out.println("■■■■■■■■■■■■■■■■■■■■");
        // ... 假装有初始化操作
        resetScene();
        System.out.println("按任意键继续...");
    }

    public static void resetScene() {
        sceneSwitchTip();
        currentGameScene = new MainMenuScene();
    }

    public static void setNextGameScene(AbstractGameScene scenes) {
        nextGameScene = scenes;
    }

    private static void checkAndSwitchToNext() {
        if (nextGameScene != null) {
            currentGameScene = nextGameScene;
            nextGameScene = null;
            sceneSwitchTip();
        }
    }

    public static void sceneSwitchTip() {
        for (int i = 0; i < 10; ++i) {
            System.out.println();
        }
    }

}
