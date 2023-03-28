package Game;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * @author Yidong Gan
 * @date 20/6/2022 3:39 pm
 * @note
 **/
public class Line {
    // 起点坐标
    int x = 460;
    int y = 100;

    // 终点坐标
    int endX = 500;
    int endY = 500;

    // 线长
    double length = 100;
    double minLength = 100;
    double maxLength = 550;

    // 角度取值为0-1，乘以pi即可得到0-pi的角度范围
    double angle = 0.5;
    int dir = 1;

    double speed = 0.004;

    // 状态 0：摇摆 1：抓取 2：收回 3：抓取返回
    int state = 0;

    GameWin frame;

    // 被抓取的物品
    Item fetchItem;

    Image hook = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("hook.png"));

    int hookX = 0;
    int hookY = 0;

    Line(GameWin frame) {
        this.frame = frame;
    }

    private void logic() {
        for (Item i : frame.itemList) {
            if (endX > i.x && endX < i.x + i.width
                    && endY > i.y && endY < i.y + i.height) {
                this.state = 3;
                fetchItem = i;
            } else if (endX > hookX && endX < hookX + hook.getWidth(null)
                    && endY > hookY && endY < hook.getHeight(null)) {
                this.state = 3;
                fetchItem = i;
            }
        }
    }

    private void paintHook(Graphics g) {
        double rotateAngle = Math.PI * angle - Math.PI / 2;
        hookX = (int) (endX - Math.cos(rotateAngle) * hook.getWidth(null) / 2);
        hookY = (int) (endY - Math.sin(rotateAngle) * hook.getWidth(null) / 2);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = AffineTransform.getTranslateInstance(hookX, hookY);
        at.rotate(rotateAngle);
        g2d.drawImage(hook, at, null);
    }

    private void paintLine(Graphics g) {
        endX = (int) (x + length * Math.cos(angle * Math.PI));
        endY = (int) (y + length * Math.sin(angle * Math.PI));
        g.drawLine(x - 1, y, endX - 1, endY);
        g.drawLine(x, y, endX, endY);
        g.drawLine(x + 1, y, endX + 1, endY);
        paintHook(g);
    }

    void paintSelf(Graphics g) {
        switch (state) {
            case 0:
                if (angle >= 0.95) {
                    dir = -1;
                } else if (angle <= 0.05) {
                    dir = 1;
                }
                angle += speed * dir;
                paintLine(g);
                break;
            case 1:
                if (length < maxLength) {
                    length += 5;
                    paintLine(g);
                    logic();
                } else {
                    state = 2;
                }
                break;
            case 2:
                if (length > minLength) {
                    length -= 5;
                    paintLine(g);
                } else {
                    state = 0;
                }
                break;
            case 3:
                if (length > minLength) {
                    if (fetchItem != null) {
                        double normWeight = (fetchItem.weight - 50.0) / (1000.0 - 50.0);
                        if (frame.player.powered) {
                            normWeight /= 2.5;
                        }
                        double pullSpeed = 5.0 - normWeight * 5.0;
                        length -= pullSpeed;
                        double rotateAngle = Math.PI * angle - Math.PI / 2;
                        fetchItem.x = hookX - fetchItem.img.getWidth(null) / 2 - (int) (rotateAngle * 30);
                        fetchItem.y = hookY + (int) (rotateAngle * 20);
                        paintLine(g);
                    }
                } else {
                    frame.player.totalScore += fetchItem.score;
                    frame.removeItem(fetchItem);
                    fetchItem = null;
                    state = 0;
                }
                break;
        }

    }
}
