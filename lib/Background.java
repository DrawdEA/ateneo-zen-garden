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
    Triangle road4;

    public Background(int xPosition, int yPosition) {
        x = xPosition;
        y = yPosition;
        sky = new Rectangle(x, y, 800, 337.4, new Color(31, 148, 254));
        grass = new Rectangle(x, y + 337.3, 800, 262.6, new Color(86, 161, 64));
    }

    public void draw(Graphics2D g2d) {
        
        sky.draw(g2d);
        grass.draw(g2d);
    }
}
