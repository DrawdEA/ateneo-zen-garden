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
    int x;
    int y;
    Rectangle border;
    Rectangle screen;
    Rectangle navbar;
    Rectangle keyboard;
    boolean isOpen;
    
    public Laptop(int x1, int y1, boolean iO) {
        isOpen = iO;
        x = x1; // TODO: actually implement the x and y coordinates so that the positions are not hardcoded x 300 y 460
        y = y1;
        border = new Rectangle(
            x, 
            y, 
            x + 200, 
            y, 
            x + 200, 
            y + 125, 
            x, 
            y + 125, 
        Color.BLACK);

        screen = new Rectangle(
            x + 5, 
            y + 5, 
            x + 195, 
            y + 5, 
            x + 195,
            y + 111, 
            x + 5, 
            y + 111, 
        Color.WHITE);

        navbar = new Rectangle(
            x + 5, 
            y + 5, 
            x + 195, 
            y + 5, 
            x + 195,
            y + 25, 
            x + 5, 
            y + 25, 
        Color.BLUE);

        keyboard = new Rectangle(
            x - 2, 
            y + 125, 
            x + 202, 
            y + 125, 
            x + 210, 
            y + 140, 
            x - 10, 
            y + 140, 
        isOpen ? Color.GRAY : Color.BLACK);
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (isOpen) {
            border.draw(g2d);
            screen.draw(g2d);
            navbar.draw(g2d);

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Serif", Font.BOLD, 15));
            g2d.drawString("ADMU Command Runner", x + 15, y + 20);
            g2d.setColor(Color.BLACK);
            g2d.drawString(">", x + 10, y + 43);
        }
        
        keyboard.draw(g2d);
    }

    public void toggleOpen() {
        isOpen = !isOpen;
        keyboard = new Rectangle(
            x, 
            y + 125, 
            x + 200, 
            y + 125, 
            x + 210, 
            y + 140, 
            x - 10, 
            y + 140, 
        isOpen ? Color.GRAY : Color.BLACK);
    }
}
