package Game;

import java.awt.*;
import java.util.List;

/**
 * @author Yidong Gan
 * @date 20/6/2022 4:22 pm
 * @note
 **/
public class Item {
    int x;
    int y;
    int width;
    int height;
    int xMax = 830;
    int xMin = 50;
    int yMax = 460;
    int yMin = 170;
    int weight;
    int score;

    Image img;

    void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    public Rectangle getRec(){
        return new Rectangle(x, y, width, height);
    }

    static boolean placeItem(List<Item> ls, Item i){
        boolean placeItem = true;
        for (Item item : ls){
            if(item.getRec().intersects(i.getRec())){
                placeItem = false;
                break;
            }
        }
        return placeItem;
    }
}
