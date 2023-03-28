package Game;

import java.awt.*;

/**
 * @author Yidong Gan
 * @date 21/6/2022 2:10 am
 * @note
 **/
public class Player {
    int totalScore = 0;
    int bomb = 10;
    boolean powered = false;
    int targetScore = 150;
    int level = 1;

    void paintSelf(Graphics g) {
        Bg.drawText(g, Color.black, 25, "积分：$" + totalScore, 50, 60);
        Bg.drawText(g, Color.black, 25, "目标积分：" + targetScore, 50, 110);
        String potionState = powered == false ? "无" : "使用中";
        Bg.drawText(g, Color.black, 25, "力量药水：" + potionState, 660, 60);
        Bg.drawText(g, Color.black, 25, "炸弹：" + bomb, 660, 110);
    }

    public int getNextScore() {
        return (int) (this.targetScore * 1.35);
    }
}
