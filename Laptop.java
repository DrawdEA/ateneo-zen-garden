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
    private static final int LAPTOP_LENGTH = 300;
    private static final int LAPTOP_HEIGHT = 180;
    private static final int BORDER_LENGTH = 5;
    private static final int NAVBAR_HEIGHT = 25;
    private static final int KEYBOARD_ANGLE_INCREMENT = 10;
    private static final int KEYBOARD_HEIGHT = 20;
    private static final int BUTTON_HEIGHT = 15;

    int x;
    int y;
    boolean isOpen;
    boolean inCommandLine;
    String command;

    Rectangle border;
    Rectangle screen;
    Rectangle navbar;
    Rectangle keyboard;
    Rectangle commandLineButton;
    Rectangle musicButton;
    
    public Laptop(int x1, int y1, boolean iO, boolean iCL, String t) {
        isOpen = iO;
        inCommandLine = iCL;
        x = x1; 
        y = y1;
        command = t;
        // x 300 y 460 -> x -> 265 560
        // 800 - 530 - 265
        // 

        border = new Rectangle(
            x, 
            y, 
            x + LAPTOP_LENGTH, 
            y, 
            x + LAPTOP_LENGTH, 
            y + LAPTOP_HEIGHT, 
            x, 
            y + LAPTOP_HEIGHT, 
        new Color(51, 51, 51));

        screen = new Rectangle(
            x + BORDER_LENGTH, 
            y + NAVBAR_HEIGHT, 
            x + LAPTOP_LENGTH - BORDER_LENGTH, 
            y + NAVBAR_HEIGHT, 
            x + LAPTOP_LENGTH - BORDER_LENGTH,
            y + LAPTOP_HEIGHT - BORDER_LENGTH * 2, 
            x + BORDER_LENGTH, 
            y + LAPTOP_HEIGHT - BORDER_LENGTH * 2, 
        new Color(30,30,30));

        keyboard = new Rectangle(
            x, 
            y + LAPTOP_HEIGHT,
            x + LAPTOP_LENGTH, 
            y + LAPTOP_HEIGHT, 
            x + LAPTOP_LENGTH + KEYBOARD_ANGLE_INCREMENT, 
            y + LAPTOP_HEIGHT + KEYBOARD_HEIGHT, 
            x - KEYBOARD_ANGLE_INCREMENT, 
            y + LAPTOP_HEIGHT + KEYBOARD_HEIGHT, 
        isOpen ? new Color(107, 107, 107) : new Color(51, 51, 51));

        commandLineButton = new Rectangle(
            x + BORDER_LENGTH, 
            y + BORDER_LENGTH, 
            x + BORDER_LENGTH + LAPTOP_LENGTH / 2, 
            y + BORDER_LENGTH, 
            x + BORDER_LENGTH + LAPTOP_LENGTH / 2,
            y + NAVBAR_HEIGHT, 
            x + BORDER_LENGTH, 
            y + NAVBAR_HEIGHT, 
        new Color(30,30,30));

        musicButton = new Rectangle(
            x + LAPTOP_LENGTH / 2, 
            y + BORDER_LENGTH, 
            x + LAPTOP_LENGTH - BORDER_LENGTH, 
            y + BORDER_LENGTH, 
            x + LAPTOP_LENGTH - BORDER_LENGTH,
            y + NAVBAR_HEIGHT, 
            x + LAPTOP_LENGTH / 2, 
            y + NAVBAR_HEIGHT, 
        new Color(30,215,96));
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (isOpen) {
            border.draw(g2d);
            screen.draw(g2d);
            commandLineButton.draw(g2d);
            musicButton.draw(g2d);

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Serif", Font.BOLD, 15));
            g2d.drawString("Command Runner", x + 15, y + 20);
            g2d.drawString("Music Player", x + 175, y + 20);
            g2d.setColor(Color.WHITE);
            if (inCommandLine) {
                g2d.setFont(new Font("Dialog", Font.BOLD, 10));
                g2d.drawString("C:\\Users\\DiestaUy\\catzenistas> " + command, x + 10, y + 43);
            } else {
                screen = new Rectangle(
                    x + BORDER_LENGTH, 
                    y + NAVBAR_HEIGHT, 
                    x + LAPTOP_LENGTH - BORDER_LENGTH, 
                    y +NAVBAR_HEIGHT, 
                    x + LAPTOP_LENGTH - BORDER_LENGTH,
                    y + LAPTOP_HEIGHT - BORDER_LENGTH * 2, 
                    x + BORDER_LENGTH, 
                    y + LAPTOP_HEIGHT - BORDER_LENGTH * 2, 
                new Color(30,215,96));
                screen.draw(g2d);
            }
        }
        
        
        keyboard.draw(g2d);
    }

    public void toggleOpen() {
        isOpen = !isOpen;
        keyboard = new Rectangle(
            x, 
            y + LAPTOP_HEIGHT,
            x + LAPTOP_LENGTH, 
            y + LAPTOP_HEIGHT, 
            x + LAPTOP_LENGTH + KEYBOARD_ANGLE_INCREMENT, 
            y + LAPTOP_HEIGHT + KEYBOARD_HEIGHT, 
            x - KEYBOARD_ANGLE_INCREMENT, 
            y + LAPTOP_HEIGHT + KEYBOARD_HEIGHT, 
        isOpen ? new Color(107, 107, 107) : new Color(51, 51, 51));
    }

    public void updateCommand(String c) {
        command = c;
    }
}
