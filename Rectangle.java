import java.awt.*;

public class Rectangle implements DrawingObject {
    int[] xCoordinates;
    int[] yCoordinates;
    Color color;
    Polygon rectangle;

    /**
     * Instantiate a square object. Order of the coordinate goes: TOP-LEFT, TOP-RIGHT, BOTTOM-RIGHT, BOTTOM-LEFT.
     * 
     * @param x1 first x coordinate of the triangle 
     * @param x2 second x coordinate of the triangle
     * @param x3 third x coordinate of the triangle
     * @param x4 fourth x coordinate of the triangle
     * @param y1 first y coordinate of the triangle
     * @param y2 second y coordinate of the triangle
     * @param y3 third y coordinate of the triangle
     * @param y4 fourth y coordinate of the triangle
     * @param c color of the triangle
     */
    public Rectangle(int x1, int x2, int x3, int x4, int y1, int y2, int y3, int y4, Color c) {
        xCoordinates = new int[4];
        yCoordinates = new int[4];
        color = c;

        xCoordinates[0] = x1;
        xCoordinates[1] = x2;
        xCoordinates[2] = x3;
        xCoordinates[3] = x4;
        yCoordinates[0] = y1;
        yCoordinates[1] = y2;
        yCoordinates[2] = y3;
        yCoordinates[3] = y4;
        rectangle = new Polygon(xCoordinates, yCoordinates, 4);
    }
    
    /**
     * Draws the rectangle shape.
     * 
     * @param g2d the Graphics2D of the component to place the drawing on
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(color);
        g2d.draw(rectangle);
        g2d.fill(rectangle);
    }
}
