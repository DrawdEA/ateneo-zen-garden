import java.awt.*;

public class Square implements DrawingObject {
    int x;
    int y;
    int size;
    Color color;

    /**
     * Instantiate a square object.
     * 
     * @param xPosition the x position of the shape
     * @param yPosition the y position of the shape
     * @param s size of the square
     * @param c color of the square
     */
    public Square(int xPosition, int yPosition, int s, Color c) {
        x = xPosition;
        y = yPosition;
        size = s;
        color = c;
    }

    /**
     * Draws the square shape.
     * 
     * @param g2d the Graphics2D of the component to place the drawing on
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(color);
        g2d.fillRect(x, y, size, size);
    }
}