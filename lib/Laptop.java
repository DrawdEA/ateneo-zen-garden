/**
 * The Laptop class is responsible for the main control area of the canvas. 
 * It is able to play music and set commands.
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
    Circle outerMarker;
    Circle innerMarker;
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

    AudioPlayer musicPlayer;

    /**
     * Instantiates a laptop object.
     * 
     * @param x1 x-axis anchor of the laptop
     * @param y1 y-axis anchor of the laptop
     * @param iO boolean if the laptop is open or not
     * @param iCL boolean if the laptop is in the command line or not
     * @param t the current command typed in the laptop
     */
    public Laptop(int x1, int y1, boolean iO, boolean iCL, String t) {
        isOpen = iO;
        inCommandLine = iCL;
        isMusicPlaying = true;
        x = x1; 
        y = y1;
        command = t;

        // Load the fonts.
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

        // Load the music player.
        try {
            musicPlayer = new AudioPlayer("assets/music");
            musicPlayer.play();
        } catch (Exception e){
            System.out.println("Error with playing music."); 
            e.printStackTrace(); 
        }
        
        // Create the framework of the laptop.
        border = new Rectangle(x, y, x + 300, y, x + 300, y + 180, x, y + 180, new Color(51, 51, 51));
        screen = new Rectangle(x + 5, y + 25, x + 300 - 5, y + 25, x + 300 - 5, y + 180 - 5 * 2, x + 5, y + 180 - 5 * 2, inCommandLine ? new Color(30,30,30) : new Color(30,215,96));
        keyboard = new Rectangle(x, y + 180,x + 300, y + 180, x + 300 + 10, y + 180 + 20, x - 10, y + 180 + 20, isOpen ? new Color(107, 107, 107) : new Color(51, 51, 51));
        commandLineButton = new Rectangle(x + 5, y + 5, x + 5 + 300 / 2, y + 5, x + 5 + 300 / 2, y + 25 + 2, x + 5, y + 25 + 2, new Color(30,30,30));
        musicButton = new Rectangle(x + 300 / 2, y + 5, x + 300 - 5, y + 5, x + 300 - 5, y + 25 + 2, x + 300 / 2, y + 25 + 2, new Color(30,215,96));

        // Marker on the song timeline.
        outerMarker = new Circle(0, y + 5 + 100 - 20 - 4, 12, new Color(30,30,30));
        innerMarker = new Circle(0, y + 5 + 100 - 20 - 2, 8, Color.WHITE);
        songTimeLine = new RoundedLine(x + 300 / 8, y + 5 + 100 - 20, x + 300 * 7 / 8, y + 5 + 100 - 20, 5, new Color(30,30,30));
        playButton = new Circle(x + 300 / 2 - 40 / 2, y + 5 + 100, 40, new Color(30,30,30));
        leftButton = new Circle(x + 300 / 2 - 40 / 2 - 50, y + 5 + 100, 40, new Color(30,215,96));
        rightButton = new Circle(x + 300 / 2 - 40 / 2 + 50, y + 5 + 100, 40, new Color(30,215,96));
        
        leftPause = new Rectangle(
            x + 300 / 2 - 7 / 2 - 5, 
            y + 5 + 100 - 20 / 2 + 40 / 2, 
            x + 300 / 2 + 7 / 2 - 5, 
            y + 5 + 100 - 20 / 2 + 40 / 2, 
            x + 300 / 2 + 7 / 2 - 5, 
            y + 5 + 100 + 20 / 2 + 40 / 2, 
            x + 300 / 2 - 7 / 2 - 5, 
            y + 5 + 100 + 20 / 2 + 40 / 2, 
        Color.WHITE);

        rightPause = new Rectangle(
            x + 300 / 2 - 7 / 2 + 5, 
            y + 5 + 100 - 20 / 2 + 40 / 2, 
            x + 300 / 2 + 7 / 2 + 5, 
            y + 5 + 100 - 20 / 2 + 40 / 2, 
            x + 300 / 2 + 7 / 2 + 5, 
            y + 5 + 100 + 20 / 2 + 40 / 2, 
            x + 300 / 2 - 7 / 2 + 5, 
            y + 5 + 100 + 20 / 2 + 40 / 2, 
        Color.WHITE);

        stop = new Triangle(
            x + 300 / 2 - 7 / 2 - 5, 
            y + 5 + 100 - 20 / 2 + 40 / 2, 
            x + 300 / 2 - 7 / 2 - 5, 
            y + 5 + 100 + 20 / 2 + 40 / 2, 
            x + 300 / 2 + 7 / 2 + 5 + 4, 
            y + 5 + 100 + 20 / 2 + 40 / 4, 
        Color.WHITE);

        lowerLeftArrow = new Line(
            x + 300 / 2 - 7 / 2 - 5 - 49, 
            y + 5 + 100 + 20 / 2 + 40 / 4, 
            x + 300 / 2 - 7 / 2 - 5 - 39, 
            y + 5 + 100 + 20 / 2 + 40 / 2, 
            5, 
        new Color(30,30,30));

        upperLeftArrow = new Line(
            x + 300 / 2 - 7 / 2 - 5 - 49, 
            y + 5 + 100 + 20 / 2 + 40 / 4, 
            x + 300 / 2 - 7 / 2 - 5 - 39, 
            y + 5 + 100 - 20 / 2 + 40 / 2, 
            5, 
        new Color(30,30,30));

        barLeftArrow = new RoundedLine(
            x + 300 / 2 - 7 / 2 - 5 - 49 - 8, 
            y + 5 + 100 + 20 + 40 / 4, 
            x + 300 / 2 - 7 / 2 - 5 - 49 - 8, 
            y + 5 + 100 - 20 / 2 + 40 / 2, 
            5,
        new Color(30,30,30));

        lowerRightArrow = new Line(
            x + 300 / 2 + 7 / 2 + 5 + 49, 
            y + 5 + 100 + 20 / 2 + 40 / 4, 
            x + 300 / 2 + 7 / 2 + 5 + 39, 
            y + 5 + 100 + 20 / 2 + 40 / 2, 
            5,
        new Color(30,30,30));

        barRightArrow = new RoundedLine(
            x + 300 / 2 + 7 / 2 + 5 + 49 + 4, 
            y + 5 + 100 + 20 + 40 / 4, 
            x + 300 / 2 + 7 / 2 + 5 + 49 + 4, 
            y + 5 + 100 - 20 / 2 + 40 / 2, 
            5,
        new Color(30,30,30));

        upperRightArrow = new Line(
            x + 300 / 2 + 7 / 2 + 5 + 49, 
            y + 5 + 100 + 20 / 2 + 40 / 4, 
            x + 300 / 2 + 7 / 2 + 5 + 39, 
            y + 5 + 100 - 20 / 2 + 40 / 2, 
            5, 
        new Color(30,30,30));
    }

    /**
     * Draws the laptop object in the correct order. The way it is drawn depends on its activated states.
     */
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
            g2d.setColor(new Color(30,30,30));
            g2d.drawString("Music", x + 175, y + 20);
            g2d.setColor(Color.WHITE);
            
            if (inCommandLine) {
                g2d.setFont(plex.deriveFont(Font.PLAIN, 10f));
                g2d.drawString("Welcome to the Command Line!", x + 10, y + 43 + 12*0);
                g2d.drawString("Start typing to enter a command", x + 10, y + 43 + 12*1);
                g2d.drawString("Outputs are in your real terminal tho", x + 10, y + 43 + 12*2); // No time to figure our JScrollPane on a canvas with readable input and all that
                g2d.drawString("To see a list of commands, run: help", x + 10, y + 43 + 12*3);

                g2d.drawString("C:\\Users\\DiestaUy\\gardZen> " + command, x + 10, y + 43 + 12*5);
            } else {
                g2d.setColor(new Color(30,30,30));
                g2d.setFont(avenir.deriveFont(Font.BOLD, 35f));
                g2d.setFont(avenir.deriveFont(Font.BOLD, 15f));
                g2d.drawString(musicPlayer.getName(), x + 300 / 9, y + 5 + 100 - 45);
                g2d.drawString(musicPlayer.getCurrentTrackTime(), x + 300 / 9, y + 5 + 100 + 25);
                g2d.drawString(musicPlayer.getTrackLength(),  x + 300 * 7 / 9, y + 5 + 100 + 25);

                songTimeLine.draw(g2d);
                
                outerMarker.setX(x + 300 / 8 + (int) (300 * 6/8 * musicPlayer.getCompletionRate()) - 6);
                innerMarker.setX(x + 300 / 8 + (int) (300 * 6/8 * musicPlayer.getCompletionRate()) - 4);
                outerMarker.draw(g2d);
                innerMarker.draw(g2d);
                
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

    /**
     * Toggles the open and close state of the laptop.
     */
    public void toggleOpen() {
        isOpen = !isOpen;
        keyboard = new Rectangle(
            x, 
            y + 180,
            x + 300, 
            y + 180, 
            x + 300 + 10, 
            y + 180 + 20, 
            x - 10, 
            y + 180 + 20, 
        isOpen ? new Color(107, 107, 107) : new Color(51, 51, 51));
    }

    /**
     * Toggles the tab between music and command line.
     * 
     * @param t boolean if it will go to music or not
     */
    public void goToMusic(boolean t) {
        inCommandLine = !t;
        screen = new Rectangle(
            x + 5, 
            y + 25, 
            x + 300 - 5, 
            y + 25, 
            x + 300 - 5,
            y + 180 - 5 * 2, 
            x + 5, 
            y + 180 - 5 * 2, 
        inCommandLine ? new Color(30,30,30) : new Color(30,215,96));
    }

    /**
     * Handles the pause and play of the music.
     */
    public void toggleMusic() {
        if (isMusicPlaying){
            musicPlayer.pause();
        } else {
            musicPlayer.play();
        }
        
        isMusicPlaying = !isMusicPlaying;
    }

    /**
     * Goes to the previous music in the playlist.
     */
    public void playPreviousMusic() {
        try {
            musicPlayer.previous();
        } catch (Exception e){
            System.out.println("Error with playing music."); 
            e.printStackTrace(); 
        }
    }

    /**
     * Goes to the next music in the playlist.
     */
    public void playNextMusic() {
        try {
            musicPlayer.skip();
        } catch (Exception e){
            System.out.println("Error with playing music."); 
            e.printStackTrace(); 
        }
    }

    /**
     * Updates the current command present in the command line.
     */
    public void updateCommand(String c) {
        command = c;
    }
    
    /**
     * Checks if the point is in the command line button.
     * 
     * @param x x value of the point
     * @param y y value of the point
     * @return boolean if it is inside or not
     */
    public boolean isInCommandLineButton(int x, int y) {
        return commandLineButton.isWithin(x, y);
    }

    /**
     * Checks if the point is in the music button.
     * 
     * @param x x value of the point
     * @param y y value of the point
     * @return boolean if it is inside or not
     */
    public boolean isInMusicButton(int x, int y) {
        return musicButton.isWithin(x, y);
    }

    /**
     * Checks if the point is in the play button.
     * 
     * @param x x value of the point
     * @param y y value of the point
     * @return boolean if it is inside or not
     */
    public boolean isInPlayButton(int x, int y) {
        return playButton.isWithin(x, y);
    }

    /**
     * Checks if the point is in the left button.
     * 
     * @param x x value of the point
     * @param y y value of the point
     * @return boolean if it is inside or not
     */
    public boolean isInLeftButton(int x, int y) {
        return leftButton.isWithin(x, y);
    }

    /**
     * Checks if the point is in the right button.
     * 
     * @param x x value of the point
     * @param y y value of the point
     * @return boolean if it is inside or not
     */
    public boolean isInRightButton(int x, int y) {
        return rightButton.isWithin(x, y);
    }

    /**
     * Checks if the laptop is in the command line tab.
     * 
     * @return whether or not the laptop is in the command line.
     */
    public boolean isInCommandLineTab() {
        return inCommandLine;
    }
}
