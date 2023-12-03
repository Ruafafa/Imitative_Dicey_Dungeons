package main;

import main.scene.AbstractGameScene;
import main.scene.MainMenuScene;

/**
 * Main class for the Dicey Dungeons game.
 */
public class DiceyDungeonsGame {

    /**
     * The current game scene.
     */
    private static AbstractGameScene currentGameScene;

    /**
     * The next game scene to be loaded.
     */
    private static AbstractGameScene nextGameScene = null;

    /**
     * Boolean flag to check if the game is running.
     */
    static boolean isGameRunning = true;

    /**
     * Main method for the game.
     *
     * @param args Command line arguments.
     * @throws InterruptedException If the thread is interrupted.
     * @throws CloneNotSupportedException If the object's class does not support the Cloneable interface.
     */
    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException {
        // Initialize the game
        init();
        // Handle exceptions
        if (currentGameScene == null) {
            System.out.println("游戏引擎异常~");
            System.exit(-1);
        }
        // Main game loop
        isGameRunning = true;
        while (isGameRunning) {
            // Preprocess the current scene
            currentGameScene.preprocess();
            // Update the game logic
            currentGameScene.updateLogic();
            // Render the UI
            currentGameScene.renderUi();
            // Check and switch to the next scene
            checkAndSwitchToNext();
        }
    }

    private static void init() {
        // Print the game title
        System.out.println("■■■■■■■■■■■■■■■■■■■■");
        System.out.println("■  Dicey Dungeons  ■");
        System.out.println("■     --!启动!--    ■");
        System.out.println("■■■■■■■■■■■■■■■■■■■■");
        // Reset the scene
        resetScene();
        System.out.println("按任意键继续...");
    }

    /**
     * 重置游戏场景。
     */
    public static void resetScene() {
        // Print a scene switch tip
        sceneSwitchTip();
        // Set the current scene to the main menu
        currentGameScene = new MainMenuScene();
    }

    /**
     * 设置下一个游戏场景。
     * @param scenes 下一个游戏场景
     */
    public static void setNextGameScene(AbstractGameScene scenes) {
        nextGameScene = scenes;
    }

    /**
     * 检查是否有下一个场景并切换到该场景。
     */
    private static void checkAndSwitchToNext() {
        if (nextGameScene != null) {
            // Switch to the next scene
            currentGameScene = nextGameScene;
            nextGameScene = null;
            // Print a scene switch tip
            sceneSwitchTip();
        }
    }

    /**
     * 切换场景时打印
     */
    public static void sceneSwitchTip() {
        for (int i = 0; i < 10; ++i) {
            System.out.println();
        }
    }

}
