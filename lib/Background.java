package lib;
import java.awt.*;
import java.awt.geom.*;

public class Background implements DrawingObject {
    int x;
    int y;
    Rectangle sky;
    Rectangle grass;
    Triangle road1;
    Triangle road2;
    Triangle road3;
    Rectangle road4;

    public Background(int xPosition, int yPosition) {
        x = xPosition;
        y = yPosition; 

>>>>>>> Stashed changes
        sky = new Rectangle(x, y, 800, 337.4, new Color(31, 148, 254));
        grass = new Rectangle(x, y + 337.3, 800, 262.6, new Color(86, 161, 64));

        road1 = new Triangle(x + 254.1, y + 16.2, x + 650, y + 498.9, x + 682.4, y + 469.2, new Color(84, 84, 84));
        road2 = new Triangle(x + 866.5, y + 517.1, x + 860, y + 566.1, x - 245.3, y + 203.5, new Color(84, 84, 84));
        road3 = new Triangle(x - 139.3, y + 427.8, x - 129.1, y + 454.4, x + 931.6, y + 152.4, new Color(84, 84, 84));
        road4 = new Rectangle(x - 66.1, y + 472.2, 755.2, 26.7, new Color(84, 84, 84));
    }

    public void draw(Graphics2D g2d) {
        
        
        grass.draw(g2d);
        road1.draw(g2d);
        road2.draw(g2d);
        road3.draw(g2d);
        road4.draw(g2d);
        sky.draw(g2d);
    }
}
