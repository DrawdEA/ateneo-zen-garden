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

public class RoundedLine implements DrawingObject {
    float thickness;
    Color color;
    Rectangle line;
    Ellipse2D.Double leftEnd;
    Ellipse2D.Double rightEnd;

    /**
     * Instantiates a horizontal or vertical rounded line.
     * 
     * @param x1 starting x position
     * @param yS starting y position
     * @param x2 ending x position
     * @param y2 ending y position
     * @param t thickness of the line
     * @param c color of the line
     */
    public RoundedLine(int x1, int y1, int x2, int y2, float t, Color c) {
        thickness = t;
        color = c;

        
        if (y1 == y2){ // This is a horizontal line
            line = new Rectangle(
                x1, 
                y1, 
                x2, 
                y2,
                x2,
                (int) (y2 + t),
                x1,
                (int) (y2 + t),
                c
            );
            leftEnd = new Ellipse2D.Double(x1 - t/2, y1, t, t);
            rightEnd = new Ellipse2D.Double(x2 - t/2, y2, t, t);
        } else if (x1 == x2){ // This is a vertical line
            line = new Rectangle(
                x1, 
                y1, 
                (int)(x2 + t), 
                y1,
                (int)(x2 + t),
                y2,
                x1,
                y2,
                c
            );
            leftEnd = new Ellipse2D.Double(x1, y1 - t/2, t, t);
            rightEnd = new Ellipse2D.Double(x2, y2 - t/2, t, t);
        } else {
            throw new Error("This class can only make horizontal or vertical straight lines");
        }
    }
    
    /**
     * Draws the line.
     * 
     * @param g2d the Graphics2D of the component to place the drawing on
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        line.draw(g2d);
        g2d.fill(leftEnd); 
        g2d.fill(rightEnd); 
    }
}
