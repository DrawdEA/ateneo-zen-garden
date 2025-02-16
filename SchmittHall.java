import java.awt.*;

public class SchmittHall implements DrawingObject {
    int x,y;
    int opacity = 100;

    public SchmittHall(int xPosition, int yPosition){
        x = xPosition;
        y = yPosition;
    }

    private Rectangle buildingBase(int x, int y, Color color) {
        return new Rectangle(
            x, 
            x + 300, 
            x + 300, 
            x, 
            y + 275, 
            y + 275, 
            y, 
            y, 
        color);
    }

    @Override
    public void draw(Graphics2D g2d) {
        buildingBase(x, y, new Color(255, 254, 224, opacity)).draw(g2d);
    }

}