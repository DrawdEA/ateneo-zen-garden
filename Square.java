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

public class Square implements DrawingObject {
    int x;
    int y;
    int size;
    Color color;
    Rectangle2D.Double square;

    /**
     * Instantiate a square object.
     * 
     * @param x1 the x position of the shape
     * @param y1 the y position of the shape
     * @param s size of the square
     * @param c color of the square
     */
    public Square(int x1, int y1, int s, Color c) {
        color = c;
        square = new Rectangle2D.Double(x1, y1, s, s);
    }

    /**
     * Draws the square shape.
     * 
     * @param g2d the Graphics2D of the component to place the drawing on
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(square);
    }
}