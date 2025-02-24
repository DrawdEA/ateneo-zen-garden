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

public class Rectangle implements DrawingObject, DetectableObject {
    Color color;
    Path2D.Double rectangle;

    /**
     * Instantiate a rectangle object.
     * 
     * @param x x coordinate of the TOP-LEFT corner
     * @param y y coordinate of the TOP-LEFT corner
     * @param l length (x-value) of the rectangle
     * @param w height (y-value) of the rectangle
     * @param c color of the rectangle
     */
    public Rectangle(double x, double y, double l, double w, Color c) {
        color = c;
        rectangle = new Path2D.Double();
        rectangle.moveTo(x, y);
        rectangle.lineTo(x + l, y);
        rectangle.lineTo(x + l, y + w);
        rectangle.lineTo(x, y + w);
        rectangle.closePath();
    }

    /**
     * Instantiate a rectangle object in a more flexible way, specifying the coordinates of each corner. 
     * Order of the coordinate goes: TOP-LEFT, TOP-RIGHT, BOTTOM-RIGHT, BOTTOM-LEFT.
     * 
     * @param x1 first x coordinate of the rectangle 
     * @param y1 first y coordinate of the rectangle
     * @param x2 second x coordinate of the rectangle
     * @param y2 second y coordinate of the rectangle
     * @param x3 third x coordinate of the rectangle
     * @param y3 third y coordinate of the rectangle
     * @param x4 fourth x coordinate of the rectangle
     * @param y4 fourth y coordinate of the rectangle
     * @param c color of the rectangle
     */
    public Rectangle(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, Color c) {
        color = c;
        rectangle = new Path2D.Double();
        rectangle.moveTo(x1, y1);
        rectangle.lineTo(x2, y2);
        rectangle.lineTo(x3, y3);
        rectangle.lineTo(x4, y4);
        rectangle.closePath();
    }
    
    /**
     * Draws the rectangle shape.
     * 
     * @param g2d the Graphics2D of the component to place the drawing on.
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(rectangle);
    }

    public boolean isWithin(int x, int y) {
        return rectangle.contains(x, y);
    }
}
