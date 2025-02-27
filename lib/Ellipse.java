/**
 * The Ellipse class creates a ellipse by setting its anchor point to the upper-left, along with its length, width, and color.
 * The class can be created with either Double or int values.
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

public class Ellipse implements DrawingObject {
    int size;
    Color color;
    Ellipse2D.Double ellipse;

    /**
     * Instantiate an ellipse object with int values.
     * 
     * @param x1 the x position of the shape
     * @param y1 the y position of the shape
     * @param s size of the circle
     * @param c color of the circle
     */
    public Ellipse(int x1, int y1, int w, int h, Color c) {
        color = c;
        ellipse = new Ellipse2D.Double(x1, y1, w, h);
    }

    /**
     * Instantiate an ellipse object with double values.
     * 
     * @param x1 the x position of the shape
     * @param y1 the y position of the shape
     * @param s size of the circle
     * @param c color of the circle
     */
    public Ellipse(double x1, double y1, double w, double h, Color c) {
        color = c;
        ellipse = new Ellipse2D.Double(x1, y1, w, h);
    }

    /**
     * Draws the ellipse shape.
     * 
     * @param g2d the Graphics2D of SceneCanvas
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(ellipse);
    }
}
