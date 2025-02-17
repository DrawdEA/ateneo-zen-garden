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

public class Triangle implements DrawingObject {
    Color color;
    Path2D.Double triangle;

    /**
     * Instantiates a triangle object. Order of the coordinate goes: BOTTOM-LEFT, BOTTOM-RIGHT, TOP.
     * 
     * @param x1 first x coordinate of the triangle
     * @param x2 second x coordinate of the triangle
     * @param x3 third x coordinate of the triangle
     * @param y1 first y coordinate of the triangle
     * @param y2 second y coordinate of the triangle
     * @param y3 third y coordinate of the triangle
     * @param c color of the triangle
     */
    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, Color c) {
        color = c;
        triangle = new Path2D.Double();
        triangle.moveTo(x1, y1);
        triangle.lineTo(x2, y2);
        triangle.lineTo(x3, y3);
        triangle.closePath();
    }

    /**
     * Draws the circle shape.
     * 
     * @param g2d the Graphics2D of the component to place the drawing on
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(triangle);
    }
}
