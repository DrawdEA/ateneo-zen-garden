package lib;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class GonzagaHall implements DrawingObject {
    double x;
    double y;

    Rectangle buildingTop;
    Rectangle building;

    Rectangle shedTop;
    Rectangle shed;

    Rectangle signTop;

    Rectangle curb;

    Rectangle background;
    Rectangle mainPath;

    Rectangle shedShadow;
    Rectangle mainShadow;

    ArrayList<Rectangle> windows;

    public GonzagaHall(double xPosition, double yPosition) {
        x = xPosition;
        y = yPosition;

        buildingTop = new Rectangle(x + 4.5, y, 587.2, 5, new Color(181, 133, 68));
        building = new Rectangle(x + 4.5, y + 5, 587.2, 181.2, new Color(238, 202, 138));

        shedTop = new Rectangle(x, y + 166, 596.2, 5, new Color(181, 133, 68));
        shed = new Rectangle(x, y + 166, 596.2, 21.2, new Color(238, 202, 138));

        curb = new Rectangle(x, y + 240.1, 596.2, 8.1, new Color(211, 203, 182));
        mainPath = new Rectangle(x + 279.3, y + 240, 57.9, 8.9, new Color(48, 48, 48));
        background = new Rectangle(x, y + 187.2, 596.2, 59.5, new Color(48, 48, 48));

        signTop = new Rectangle(x + 251.2, y + 150, 123.6, 5, new Color(181, 133, 68));
    }

    public void draw(Graphics2D g2d) {
        building.draw(g2d);
        buildingTop.draw(g2d);
        shed.draw(g2d);
        shedTop.draw(g2d);
        background.draw(g2d);
        curb.draw(g2d);
        mainPath.draw(g2d);
        
        signTop.draw(g2d);
    }
}
