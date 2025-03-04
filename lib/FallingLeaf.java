/**
 * The FallingLeaf class is responsible for creating and animating a falling leaf.
 * Its size, color, falling direction and speed can be customized.
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
import java.util.*;

public class FallingLeaf implements DrawingObject {
    double x;
    double y;
    double scale;
    Color color;
    int state;
    Random random;
    int fallingXIncrement;
    int fallingYIncrement;
    Ellipse fallingLeaf;

    /**
     * Constructs a FallingLeaf object.
     * 
     * @param xPosition x-axis anchor of the leaf
     * @param yPosition y-axis anchor of the leaf
     * @param c color of the leaf
     * @param s the scale of the leaf
     * @param fX its falling direction and the speed thereof
     * @param fY its falling speed
     */
    public FallingLeaf(double xPosition, double yPosition, Color c, double s, int fX, int fY) {
        x = xPosition;
        y = yPosition;
        color = c;
        scale = s;
        fallingXIncrement = fX;
        fallingYIncrement = fY;
        random = new Random();
        state = random.nextInt(8);
    }

    /**
     * Draws the falling leaf depending on its state/frame.
     */
    public void draw(Graphics2D g2d) {
        AffineTransform resetTransform = g2d.getTransform();
        switch(state) {
            case 0:
                g2d.rotate(Math.toRadians(13.5), x + 16.2 * scale  + 200.8 * scale / 2, y - 19.8 * scale  + 132.6 * scale / 2);
                fallingLeaf = new Ellipse(x + 16.2 * scale , y - 19.8 * scale , 200.8 * scale, 132.6 * scale, color);
                fallingLeaf.draw(g2d);
                break;
            case 1:
                g2d.rotate(Math.toRadians(1), x + 29.5 * scale  + 214.9 * scale / 2, y - 2 * scale  + 106.3 * scale / 2);
                fallingLeaf = new Ellipse(x + 29.5 * scale , y - 2 * scale , 214.9 * scale, 106.3 * scale, color);
                fallingLeaf.draw(g2d);
                break;
            case 2:
                g2d.rotate(Math.toRadians(-13.8), x + 60 * scale  + 222.5 * scale / 2, y + 33.4 * scale  + 99.4 * scale / 2);
                fallingLeaf = new Ellipse(x + 60 * scale , y + 33.4 * scale , 222.5 * scale, 99.4 * scale, color);
                fallingLeaf.draw(g2d);
                break;
            case 3:
                g2d.rotate(Math.toRadians(-13.8), x + 112.8 * scale  + 200.8 * scale / 2, y + 28.9 * scale  + 113.5 * scale / 2);
                fallingLeaf = new Ellipse(x + 112.8 * scale , y + 28.9 * scale , 200.8 * scale, 113.5 * scale, color);
                fallingLeaf.draw(g2d);
                break;
            case 4:
                g2d.rotate(Math.toRadians(-27.4), x + 108.5 * scale  + 209.9 * scale / 2, y + 28.9 * scale  + 113.5 * scale / 2);
                fallingLeaf = new Ellipse(x + 108.5 * scale , y + 28.9 * scale , 209.9 * scale, 113.5 * scale, color);
                fallingLeaf.draw(g2d);
                break;
            case 5:
                g2d.rotate(Math.toRadians(-1.8), x + 73.7 * scale  + 230.1 * scale / 2, y + 6.1 * scale  + 96.8 * scale / 2);
                fallingLeaf = new Ellipse(x + 73.7 * scale , y + 6.1 * scale , 230.1 * scale, 96.8 * scale, color);
                fallingLeaf.draw(g2d);
                break;
            case 6:
                g2d.rotate(Math.toRadians(12.4), x + 64.8 * scale  + 214.4 * scale / 2, y - 6.9 * scale  + 98.1 * scale / 2);
                fallingLeaf = new Ellipse(x + 64.8 * scale , y - 6.9 * scale , 214.4 * scale, 98.1 * scale, color);
                fallingLeaf.draw(g2d);
                break;
            case 7:
                g2d.rotate(Math.toRadians(23.2), x + 40.3 * scale  + 200.8 * scale / 2, y - 16 * scale  + 113.5 * scale / 2);
                fallingLeaf = new Ellipse(x + 40.3 * scale , y - 16 * scale , 200.8 * scale, 113.5 * scale, color);
                fallingLeaf.draw(g2d);
                break;
        }
        g2d.setTransform(resetTransform);

        // Update the state and move the leaf.
        state++;
        if (state > 7) state = 0;
        x = x + fallingXIncrement;
        y = y + fallingYIncrement;
    }
}
