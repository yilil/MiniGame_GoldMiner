package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Yidong Gan
 * @date 21/6/2022 3:39 pm
 * @note
 **/
public class Shop extends Game {
    Shop(GameWin gw) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        JLabel l1 = new JLabel("商店");
        l1.setFont(new Font("Times", Font.BOLD, 50));
        l1.setForeground(Color.yellow);
        l1.setBounds(windowWidth / 2 - 50, 80, 200, 50);

        JLabel l2 = new JLabel("当前积分：$" + gw.player.totalScore);
        l2.setFont(new Font("Times", Font.BOLD, 18));
        l2.setForeground(Color.yellow);
        l2.setBounds(windowWidth / 2 - 70, 140, 400, 50);

        JLabel l3 = new JLabel("下一关积分：$" + gw.player.getNextScore());
        l3.setFont(new Font("Times", Font.BOLD, 18));
        l3.setForeground(Color.yellow);
        l3.setBounds(windowWidth / 2 - 70, 170, 400, 50);

        int potionPrice = (int) (250 + (Math.random() * (500 - 250)));
        JButton b1 = new JButton(String.format("购买药水（价格：$%s）", potionPrice));
        b1.setBounds(windowWidth / 2 - 200, 250, 400, 50);
        b1.setBackground(Color.yellow);
        b1.setFont(new Font("Times", Font.BOLD, 18));

        int bombPrice = (int) (200 + (Math.random() * (450 - 200)));
        JButton b2 = new JButton(String.format("购买炸弹（价格：$%s）", bombPrice));
        b2.setBounds(windowWidth / 2 - 200, 310, 400, 50);
        b2.setBackground(Color.green);
        b2.setFont(new Font("Times", Font.BOLD, 18));

        JButton b3 = new JButton("下一关");
        b3.setBounds(windowWidth / 2 - 200, 370, 400, 50);
        b3.setBackground(Color.green);
        b3.setFont(new Font("Times", Font.BOLD, 18));

        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        setContentPane(panel);

        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gw.player.totalScore >= potionPrice) {
                    gw.player.totalScore -= potionPrice;
                    l2.setText("当前积分：$" + gw.player.totalScore);
                    JButton source = (JButton) e.getSource();
                    source.setEnabled(false);
                    gw.player.powered = true;
                }
            }
        });

        b2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gw.player.totalScore >= bombPrice) {
                    gw.player.totalScore -= bombPrice;
                    l2.setText("当前积分：$" + gw.player.totalScore);
                    JButton source = (JButton) e.getSource();
                    source.setEnabled(false);
                    gw.player.bomb++;
                }
            }
        });

        b3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gw.nextLevel();
            }
        });

        setTitle(title);
        setSize(windowWidth, windowHeight);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Shop(new GameWin());
    }
}
