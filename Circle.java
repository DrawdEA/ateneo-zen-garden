import java.awt.*;

public class Circle implements DrawingObject {
    int x;
    int y;
    int size;
    Color color;

    /**
     * Instantiate a circle object.
     * 
     * @param xPosition the x position of the shape
     * @param yPosition the y position of the shape
     * @param s size of the circle
     * @param c color of the circle
     */
    public Circle(int xPosition, int yPosition, int s, Color c) {
        x = xPosition;
        y = yPosition;
        size = s;
        color = c;
    }

    /**
     * Draws the circle shape.
     * 
     * @param g2d the Graphics2D of the component to place the drawing on
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(color);
        g2d.fillOval(x, y, size, size);
    }
}
