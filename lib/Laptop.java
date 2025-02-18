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
import java.io.File;
import java.io.IOException;

public class Laptop implements DrawingObject {
    private static final int LAPTOP_LENGTH = 300;
    private static final int LAPTOP_HEIGHT = 180;
    private static final int BORDER_LENGTH = 5;
    private static final int NAVBAR_HEIGHT = 25;
    private static final int KEYBOARD_ANGLE_INCREMENT = 10;
    private static final int KEYBOARD_HEIGHT = 20;
    private static final int NAVBAR_PADDING = 2;
    private static final int BUTTON_Y = 100;
    private static final int BUTTON_SIZE = 40;
    private static final int BUTTON_GAP = 50;
    private static final int PAUSE_X_SIZE = 7;
    private static final int PAUSE_Y_SIZE = 20;
    private static final int PAUSE_GAP = 5;

    int x;
    int y;
    boolean isOpen;
    boolean inCommandLine;
    boolean isMusicPlaying;
    String command;

    Rectangle border;
    Rectangle screen;
    Rectangle navbar;
    Rectangle keyboard;
    Rectangle commandLineButton;
    Rectangle musicButton;

    RoundedLine songTimeLine;
    Circle playButton;
    Circle leftButton;
    Circle rightButton;
    Rectangle leftPause;
    Rectangle rightPause;
    Triangle stop;
    Line upperLeftArrow;
    Line lowerLeftArrow;
    RoundedLine barLeftArrow;
    Line upperRightArrow;
    Line lowerRightArrow;
    RoundedLine barRightArrow;

    File avenirFile, plexFile;
    Font avenir, plex;

    public Laptop(int x1, int y1, boolean iO, boolean iCL, String t) {
        isOpen = iO;
        inCommandLine = iCL;
        isMusicPlaying = false;
        x = x1; 
        y = y1;
        command = t;

        try {
            avenirFile = new File("assets/fonts/Avenir/AvenirLTStd-Black.otf");
            plexFile = new File("assets/fonts/Plex/IBMPlexMono-Regular.ttf");

            avenir = Font.createFont(Font.TRUETYPE_FONT, avenirFile);
            plex = Font.createFont(Font.TRUETYPE_FONT, plexFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException ex) {
            ex.printStackTrace();
        }
    
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
        inCommandLine ? new Color(30,30,30) : new Color(30,215,96));

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
            y + NAVBAR_HEIGHT + NAVBAR_PADDING, 
            x + BORDER_LENGTH, 
            y + NAVBAR_HEIGHT + NAVBAR_PADDING, 
        new Color(30,30,30));

        musicButton = new Rectangle(
            x + LAPTOP_LENGTH / 2, 
            y + BORDER_LENGTH, 
            x + LAPTOP_LENGTH - BORDER_LENGTH, 
            y + BORDER_LENGTH, 
            x + LAPTOP_LENGTH - BORDER_LENGTH,
            y + NAVBAR_HEIGHT + NAVBAR_PADDING, 
            x + LAPTOP_LENGTH / 2, 
            y + NAVBAR_HEIGHT + NAVBAR_PADDING, 
        new Color(30,215,96));

        songTimeLine = new RoundedLine(
            x + LAPTOP_LENGTH / 8, 
            y + BORDER_LENGTH + BUTTON_Y - 20, 
            x + LAPTOP_LENGTH * 7 / 8, 
            y + BORDER_LENGTH + BUTTON_Y - 20, 
            5, 
        new Color(30,30,30));

        playButton = new Circle(
            x + LAPTOP_LENGTH / 2 - BUTTON_SIZE / 2, 
            y + BORDER_LENGTH + BUTTON_Y, 
            BUTTON_SIZE, 
        new Color(30,30,30));

        leftButton = new Circle(
            x + LAPTOP_LENGTH / 2 - BUTTON_SIZE / 2 - BUTTON_GAP, 
            y + BORDER_LENGTH + BUTTON_Y, 
            BUTTON_SIZE, 
        new Color(30,215,96));

        rightButton = new Circle(
            x + LAPTOP_LENGTH / 2 - BUTTON_SIZE / 2 + BUTTON_GAP, 
            y + BORDER_LENGTH + BUTTON_Y, 
            BUTTON_SIZE, 
        new Color(30,215,96));

        leftPause = new Rectangle(
            x + LAPTOP_LENGTH / 2 - PAUSE_X_SIZE / 2 - PAUSE_GAP, 
            y + BORDER_LENGTH + BUTTON_Y - PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            x + LAPTOP_LENGTH / 2 + PAUSE_X_SIZE / 2 - PAUSE_GAP, 
            y + BORDER_LENGTH + BUTTON_Y - PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            x + LAPTOP_LENGTH / 2 + PAUSE_X_SIZE / 2 - PAUSE_GAP, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            x + LAPTOP_LENGTH / 2 - PAUSE_X_SIZE / 2 - PAUSE_GAP, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
        Color.WHITE);

        rightPause = new Rectangle(
            x + LAPTOP_LENGTH / 2 - PAUSE_X_SIZE / 2 + PAUSE_GAP, 
            y + BORDER_LENGTH + BUTTON_Y - PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            x + LAPTOP_LENGTH / 2 + PAUSE_X_SIZE / 2 + PAUSE_GAP, 
            y + BORDER_LENGTH + BUTTON_Y - PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            x + LAPTOP_LENGTH / 2 + PAUSE_X_SIZE / 2 + PAUSE_GAP, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            x + LAPTOP_LENGTH / 2 - PAUSE_X_SIZE / 2 + PAUSE_GAP, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
        Color.WHITE);

        stop = new Triangle(
            x + LAPTOP_LENGTH / 2 - PAUSE_X_SIZE / 2 - PAUSE_GAP, 
            y + BORDER_LENGTH + BUTTON_Y - PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            x + LAPTOP_LENGTH / 2 - PAUSE_X_SIZE / 2 - PAUSE_GAP, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            x + LAPTOP_LENGTH / 2 + PAUSE_X_SIZE / 2 + PAUSE_GAP + 4, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 4, 
        Color.WHITE);

        lowerLeftArrow = new Line(
            x + LAPTOP_LENGTH / 2 - PAUSE_X_SIZE / 2 - PAUSE_GAP - 49, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 4, 
            x + LAPTOP_LENGTH / 2 - PAUSE_X_SIZE / 2 - PAUSE_GAP - 39, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            5, 
        new Color(30,30,30));

        upperLeftArrow = new Line(
            x + LAPTOP_LENGTH / 2 - PAUSE_X_SIZE / 2 - PAUSE_GAP - 49, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 4, 
            x + LAPTOP_LENGTH / 2 - PAUSE_X_SIZE / 2 - PAUSE_GAP - 39, 
            y + BORDER_LENGTH + BUTTON_Y - PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            5, 
        new Color(30,30,30));

        barLeftArrow = new RoundedLine(
            x + LAPTOP_LENGTH / 2 - PAUSE_X_SIZE / 2 - PAUSE_GAP - 49 - 8, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE + BUTTON_SIZE / 4, 
            x + LAPTOP_LENGTH / 2 - PAUSE_X_SIZE / 2 - PAUSE_GAP - 49 - 8, 
            y + BORDER_LENGTH + BUTTON_Y - PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            5,
        new Color(30,30,30));

        lowerRightArrow = new Line(
            x + LAPTOP_LENGTH / 2 + PAUSE_X_SIZE / 2 + PAUSE_GAP + 49, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 4, 
            x + LAPTOP_LENGTH / 2 + PAUSE_X_SIZE / 2 + PAUSE_GAP + 39, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            5,
        new Color(30,30,30));

        barRightArrow = new RoundedLine(
            x + LAPTOP_LENGTH / 2 + PAUSE_X_SIZE / 2 + PAUSE_GAP + 49 + 4, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE + BUTTON_SIZE / 4, 
            x + LAPTOP_LENGTH / 2 + PAUSE_X_SIZE / 2 + PAUSE_GAP + 49 + 4, 
            y + BORDER_LENGTH + BUTTON_Y - PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            5,
        new Color(30,30,30));

        upperRightArrow = new Line(
            x + LAPTOP_LENGTH / 2 + PAUSE_X_SIZE / 2 + PAUSE_GAP + 49, 
            y + BORDER_LENGTH + BUTTON_Y + PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 4, 
            x + LAPTOP_LENGTH / 2 + PAUSE_X_SIZE / 2 + PAUSE_GAP + 39, 
            y + BORDER_LENGTH + BUTTON_Y - PAUSE_Y_SIZE / 2 + BUTTON_SIZE / 2, 
            5, 
        new Color(30,30,30));
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (isOpen) {
            border.draw(g2d);
            commandLineButton.draw(g2d);
            musicButton.draw(g2d);
            screen.draw(g2d);

            g2d.setColor(Color.WHITE);
            g2d.setFont(avenir.deriveFont(Font.BOLD, 15f));
            g2d.drawString("CMD", x + 15, y + 20);
            g2d.drawString("Music", x + 175, y + 20);
            g2d.setColor(Color.WHITE);
            
            if (inCommandLine) {
                g2d.setFont(plex.deriveFont(Font.PLAIN, 10f));
                g2d.drawString("C:\\Users\\DiestaUy\\gardZen> " + command, x + 10, y + 43);
            } else {
                songTimeLine.draw(g2d);
                playButton.draw(g2d);
                leftButton.draw(g2d);
                rightButton.draw(g2d);

                lowerLeftArrow.draw(g2d);
                upperLeftArrow.draw(g2d);
                barLeftArrow.draw(g2d);
                lowerRightArrow.draw(g2d);
                upperRightArrow.draw(g2d);
                barRightArrow.draw(g2d);
                
                if (isMusicPlaying) {
                    leftPause.draw(g2d);
                    rightPause.draw(g2d);
                } else {
                    stop.draw(g2d);
                }
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

    public void goToMusic(boolean t) {
        inCommandLine = !t;
        screen = new Rectangle(
            x + BORDER_LENGTH, 
            y + NAVBAR_HEIGHT, 
            x + LAPTOP_LENGTH - BORDER_LENGTH, 
            y + NAVBAR_HEIGHT, 
            x + LAPTOP_LENGTH - BORDER_LENGTH,
            y + LAPTOP_HEIGHT - BORDER_LENGTH * 2, 
            x + BORDER_LENGTH, 
            y + LAPTOP_HEIGHT - BORDER_LENGTH * 2, 
        inCommandLine ? new Color(30,30,30) : new Color(30,215,96));
    }

    public void toggleMusic() {
        isMusicPlaying = !isMusicPlaying; // TODO: ADD STOP HERE AND PLAY, IT"S THE TOGGLE
    }

    public void playPreviousMusic() {
        System.out.println("PLAY PREVIOUS MUSIC.."); // TODO: ADD STUFF HERE FOR PRESSING PREVIOUS
    }

    public void playNextMusic() {
        System.out.println("PLAY NEXT MUSIC.."); // TODO: ADD STUFF HERE FOR PRESSING NEXT
    }

    public void updateCommand(String c) {
        command = c;
    }

    public boolean isInCommandLineButton(int x, int y) {
        return commandLineButton.isWithin(x, y);
    }

    public boolean isInMusicButton(int x, int y) {
        return musicButton.isWithin(x, y);
    }

    public boolean isInPlayButton(int x, int y) {
        return playButton.isWithin(x, y);
    }

    public boolean isInLeftButton(int x, int y) {
        return leftButton.isWithin(x, y);
    }

    public boolean isInRightButton(int x, int y) {
        return rightButton.isWithin(x, y);
    }
}
