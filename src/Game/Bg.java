package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URLClassLoader;

/**
 * @author Yidong Gan
 * @date 20/6/2022 3:17 pm
 * @note
 **/
public class Bg {
    Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("bg.png"));
    Image topBg = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("topBg.png"));
    Image miner = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("miner.png"));

    void paintSelf(Graphics g) {
        g.drawImage(topBg, 0, 0, null);
        g.drawImage(bg, 0, 125, null);
        g.drawImage(miner, 350, 25, null);

    }

    public static void drawText(Graphics g, Color color,int size, String str, int x, int y) {
        g.setColor(color);
        g.setFont(new Font("Times", Font.BOLD, size));
        g.drawString(str, x, y);
    }
}
