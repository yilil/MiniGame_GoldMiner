package Game;

/**
 * @author Yidong Gan
 * @date 21/6/2022 12:41 pm
 * @note
 **/

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameStart extends Game {
    GameStart(String[] args) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        JLabel l1;
        if(args.length > 0){
            l1 = new JLabel(args[0]);
        }else{
            l1 = new JLabel("黄金矿工");
        }
        l1.setFont(new Font("Times", Font.BOLD, 50));
        l1.setForeground(Color.yellow);
        l1.setBounds(windowWidth / 2 - 100, 150, 200, 50);
        JButton b1;
        if(args.length > 0){
            b1 = new JButton("重新开始");
        }else{
            b1 = new JButton("开始游戏");
        }
        b1.setBounds(windowWidth / 2 - 75, 250, 150, 50);
        b1.setBackground(Color.yellow);
        b1.setFont(new Font("Times", Font.BOLD, 18));
        JButton b2 = new JButton("退出");
        b2.setBounds(windowWidth / 2 - 75, 330, 150, 50);
        b2.setBackground(Color.green);
        b2.setFont(new Font("Times", Font.BOLD, 18));
        panel.add(l1);
        panel.add(b1);
        panel.add(b2);
        setContentPane(panel);

        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameWin.main(null);
                dispose();
            }
        });

        b2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        setTitle(title);
        setSize(windowWidth, windowHeight);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        new GameStart(args);
    }
}
