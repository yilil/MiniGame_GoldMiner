package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yidong Gan
 * @date 20/6/2022 3:05 pm
 * @note
 **/
public class GameWin extends Game {
    long startTime;
    long endTime;
    long time;
    int timeLimit = 60;

    Bg bg = new Bg();
    Line line = new Line(this);
    List<Item> itemList = new ArrayList<>();
    Player player = new Player();

    {
        for (int i = 0; i < 5; i++) {
            double rand = Math.random();
            Gold g;
            if (rand <= 0.3) {
                g = new GoldMini();
            } else if (rand > 0.3 && rand <= 0.7) {
                g = new Gold();
            } else {
                g = new GoldMax();
            }
            if (Item.placeItem(itemList, g)) {
                itemList.add(g);
            } else {
                i--;
            }
        }
    }

    {
        for (int i = 0; i < 5; i++) {
            double rand = Math.random();
            Rock r;
            if (rand <= 0.3) {
                r = new RockMini();
            } else if (rand > 0.3 && rand <= 0.7) {
                r = new Rock();
            } else {
                r = new RockMax();
            }
            if (Item.placeItem(itemList, r)) {
                itemList.add(r);
            } else {
                i--;
            }
        }
    }

    // 画布 用来解决屏幕闪烁问题 把components都画在画布上然后一并渲染
    Image offScreenImage;

    void init() {
        this.setVisible(true);
        this.setSize(windowWidth, windowHeight);
        this.setLocationRelativeTo(null);
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        // 按空格抓取物体
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == 32) {
                    if (line.state == 0) {
                        line.state = 1;
                    }
                }
            }
        });

        // 鼠标左键扔出炸弹
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == 1 && player.bomb > 0 && line.state == 3){
                    player.totalScore -= line.fetchItem.score;
                    player.bomb -= 1;
                    line.length = line.minLength;
                }
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        offScreenImage = createImage(windowWidth, windowHeight);
        Graphics gImage = offScreenImage.getGraphics();
        bg.paintSelf(gImage);
        for (Item i : itemList) {
            i.paintSelf(gImage);
        }
        line.paintSelf(gImage);
        player.paintSelf(gImage);
        this.paintSelf(gImage);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void paintSelf(Graphics g) {
        Bg.drawText(g, Color.blue, 25, "剩余时间：" + time + "秒", 50, 520);
    }

    public void nextLevel() {
        GameWin gw = new GameWin();
        gw.player = player;
        gw.player.level += 1;
        gw.player.targetScore = gw.player.getNextScore();
        gw.init();
        gw.run();
        dispose();
    }

    public void removeItem(Item i) {
        itemList.remove(i);
    }

    public void run() {
        startTime = System.currentTimeMillis();
        Timer t = new Timer(10, (ActionEvent e) -> {
            endTime = System.currentTimeMillis();
            time = timeLimit - (int) (endTime - startTime) / 1000;
            if (time <= 0) {
                ((Timer)e.getSource()).stop();
                if (player.totalScore >= player.targetScore) {
                    player.powered = false;
                    new Shop(this);
                } else {
                    GameStart.main(new String[]{"游戏失败"});
                    dispose();
                }
            }
            repaint();
        });
        t.start();
    }

    public static void main(String[] args) {
        GameWin gw = new GameWin();
        gw.init();
        gw.run();
    }
}
