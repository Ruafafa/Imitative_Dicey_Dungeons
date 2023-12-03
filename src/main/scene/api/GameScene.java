package main.scene.api;
/**
 * 这是GameScene界面。
 * 它代表游戏中的一个场景。
 * 该接口包括用于预处理场景、更新游戏逻辑和呈现用户界面的方法。
 */
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
    void renderUi() throws InterruptedException;

}
