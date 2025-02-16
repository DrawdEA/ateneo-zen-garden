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

public class Laptop implements DrawingObject {
    int xPosition;
    int yPosition;
    Rectangle border;
    Rectangle screen;
    Rectangle keyboard;
    boolean isOpen;
    
    public Laptop(int x, int y, boolean iO) {
        isOpen = iO;
        xPosition = x; // TODO: actually implement the x and y coordinates so that the positions are not hardcoded
        yPosition = y;
        border = new Rectangle(300, 500, 500, 300, 460, 460, 585, 585, Color.BLACK);
        screen = new Rectangle(305, 495, 495, 305, 465, 465, 571, 571, Color.WHITE);
        keyboard = new Rectangle(298, 502, 510, 290, 585, 585, 600, 600, isOpen ? Color.GRAY : Color.BLACK);
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (isOpen) {
            border.draw(g2d);
            screen.draw(g2d);
        }
        
        keyboard.draw(g2d);
    }

    public void toggleOpen() {
        isOpen = !isOpen;
        keyboard = new Rectangle(298, 502, 510, 290, 585, 585, 600, 600, isOpen ? Color.GRAY : Color.BLACK);
    }
}
