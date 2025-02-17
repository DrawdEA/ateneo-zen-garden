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

import java.awt.*;
import java.awt.geom.*;

public class Rectangle implements DrawingObject {
    Color color;
    Path2D.Double rectangle;

    /**
     * Instantiate a rectangle object. Order of the coordinate goes: TOP-LEFT, TOP-RIGHT, BOTTOM-RIGHT, BOTTOM-LEFT.
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
    public Rectangle(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, Color c) {
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
