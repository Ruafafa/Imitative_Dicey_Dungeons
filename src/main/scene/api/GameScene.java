package main.scene.api;

public interface GameScene {
    /**
     * 预先显示
     */
    void preprocess() throws InterruptedException, CloneNotSupportedException;
    /**
     * 游戏逻辑
     */
    void updateLogic() throws InterruptedException;

    /**
     * 游戏逻辑
     */
    void renderUI() throws InterruptedException;

}
