package Game;

import java.awt.*;

/**
 * @author Yidong Gan
 * @date 20/6/2022 4:25 pm
 * @note
 **/
public class Gold extends Item{
    Gold(){
        this.x = xMin + (int) (Math.random() * (xMax - xMin));
        this.y = yMin + (int) (Math.random() * (yMax - yMin));
        this.width = 66;
        this.height = 60;
        this.weight = 700;
        this.img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("goldNormal.png"));
        this.score = 150;
    }
}

class GoldMini extends Gold{
    GoldMini(){
        this.width = 22;
        this.height = 20;
        this.weight = 300;
        this.img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("goldMini.png"));
        this.score = 50;
    }
}

class GoldMax extends Gold{
    GoldMax(){
        this.width = 160;
        this.height = 145;
        this.weight = 920;
        this.img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("goldMax.png"));
        this.score = 500;
    }
}