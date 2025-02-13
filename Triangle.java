import java.awt.*;

public class Triangle implements DrawingObject {
    int[] xCoordinates;
    int[] yCoordinates;
    Color color;
    Polygon triangle;

    /**
     * Instantiates a triangle object.
     * 
     * @param x1 first x coordinate of the triangle
     * @param x2 second x coordinate of the triangle
     * @param x3 third x coordinate of the triangle
     * @param y1 first y coordinate of the triangle
     * @param y2 second y coordinate of the triangle
     * @param y3 third y coordinate of the triangle
     * @param c  color of the triangle
     */
    public Triangle(int x1, int x2, int x3, int y1, int y2, int y3, Color c) {
        xCoordinates = new int[3];
        yCoordinates = new int[3];
        color = c;

        xCoordinates[0] = x1;
        xCoordinates[1] = x2;
        xCoordinates[2] = x3;
        yCoordinates[0] = y1;
        yCoordinates[1] = y2;
        yCoordinates[2] = y3;
        triangle = new Polygon(xCoordinates, yCoordinates, 3);
    }
    
    /**
     * Draws the circle shape.
     * 
     * @param g2d the Graphics2D of the component to place the drawing on
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(color);
        g2d.draw(triangle);
        g2d.fill(triangle);
    }
}
