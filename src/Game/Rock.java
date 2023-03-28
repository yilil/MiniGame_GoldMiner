package Game;

import java.awt.*;

/**
 * @author Yidong Gan
 * @date 21/6/2022 1:16 am
 * @note
 **/
public class Rock extends Item{
    Rock(){
        this.x = xMin + (int) (Math.random() * (xMax - xMin));
        this.y = yMin + (int) (Math.random() * (yMax - yMin));
        this.width = 52;
        this.height = 52;
        this.weight = 800;
        this.img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("rockNormal.png"));
        this.score = 3;
    }
}

class RockMini extends Rock{
    RockMini(){
        this.width = 22;
        this.height = 21;
        this.weight = 550;
        this.img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("rockMini.png"));
        this.score = 1;
    }
}

class RockMax extends Rock{
    RockMax(){
        this.width = 165;
        this.height = 159;
        this.weight = 960;
        this.img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("rockMax.png"));
        this.score = 10;
    }
}
