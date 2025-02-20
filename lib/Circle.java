/**
 * TODO: Description
 * 
 * @author Edward Joshua M. Diesta (241571), Charles Joshua T. Uy (244644)
 * @version March 3, 2025
 * 
 * We have not discussed the Java language code in our program 
 * with anyone other than our instructor or the teaching assistants 
 * assigned to this course.
 * 
 * We have not used Java language code obtained from another student, 
 * or any other unauthorized source, either modified or unmodified.
 * 
 * If any Java language code or documentation used in our program 
 * was obtained from another source, such as a textbook or website, 
 * that has been clearly noted with a proper citation in the comments 
 * of our program.
 */

package lib;
import java.awt.*;
import java.awt.geom.*;

public class Circle implements DrawingObject, DetectableObject {
    int x;
    int y;
    int size;
    Color color;
    Ellipse2D.Double circle;

    /**
     * Instantiate a circle object.
     * 
     * @param x1 the x position of the shape
     * @param y1 the y position of the shape
     * @param s size of the circle
     * @param c color of the circle
     */
    public Circle(int x, int y, int size, Color c) {
        color = c;
        this.x = x;
        this.y = y;
        this.size = size;
        circle = new Ellipse2D.Double(x, y, size, size);
    }

    /**
     * Draws the circle shape.
     * 
     * @param g2d the Graphics2D of the component to place the drawing on
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(circle);
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
        circle = new Ellipse2D.Double(x, y, size, size);
    }

    public void setX(int x) {
        this.x = x;
        circle = new Ellipse2D.Double(x, y, size, size);
    }

    public void setY(int y) {
        this.y = y;
        circle = new Ellipse2D.Double(x, y, size, size);
    }

    @Override
    public boolean isWithin(int x, int y) {
        return circle.contains(x, y);
    }
}
