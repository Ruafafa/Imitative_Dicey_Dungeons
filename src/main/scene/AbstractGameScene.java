package main.scene;

import main.common.AsciiColor;
import main.common.LineStyle;
import main.scene.api.GameScene;
import main.utils.PrintStyleUtil;

import java.util.Scanner;

/**
 * 抽象引擎模板
 */
public abstract class AbstractGameScene implements GameScene {

    /**
     * 存储用户最近输入
     */
    public String userInput = null;

    /**
     * 预先显示
     */
    public abstract void preprocess() throws InterruptedException, CloneNotSupportedException;

    /**
     * 更新主页跳转
     */
    public abstract void updateLogic() throws InterruptedException;

    /**
     * 根据数据更新主页画面
     */
    public abstract void renderUI() throws InterruptedException;

    /**
     * 获取用户 键盘输入
     */
    public String getUserInput() {
        return new Scanner(System.in).nextLine();
    }



    /**
     * 打印 Banner
     * :jdk11+ 使用 "——".repeat(cnt);生成更好
     * @param msg
     * @param style 打印格式枚举
     */
    public void showBanner(String msg, LineStyle style) {
        int bannerMinWidth = 20;
        if (msg.length() > bannerMinWidth) {
            bannerMinWidth = msg.length() * 2;
        }
        // 生成 row 横线
        StringBuilder lineBuilder = new StringBuilder();
        for (int i = 0; i < bannerMinWidth; i++) {
            lineBuilder.append(style);
        }
        String row = lineBuilder.toString();
        // 打印 Banner
        System.out.println(row);
        PrintStyleUtil.alignCenter(msg, bannerMinWidth);
        System.out.println(row);
    }

    public Integer inputAndCheckInteger() {
        Integer i;
        while (true) {
            try {
                i = Integer.valueOf(getUserInput());
                break;
            } catch (NumberFormatException e) {
                System.out.print(AsciiColor.RED + "【YOU】x 请输入数字!\n:" + AsciiColor.RESET);
            }
        }
        return i;
    }

}
