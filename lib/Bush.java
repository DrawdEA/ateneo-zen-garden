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

public class Bush implements DrawingObject {
    int x,y;
    Color base, midtone, highlight;
    int opacity = 255;

    public Bush(int xPosition, int yPosition, Color base, Color midtone, Color highlight) {
        x = xPosition;
        y = yPosition;
        this.base = base;
        this.midtone = midtone;
        this.highlight = highlight;
    }

    @Override
    public void draw(Graphics2D g2d) {
        
    }
}