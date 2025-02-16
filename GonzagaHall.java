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
import java.util.ArrayList;

public class GonzagaHall implements DrawingObject {
    int x, y;
    int opacity = 255;
    
    public GonzagaHall(int xPosition, int yPosition){
        x = xPosition;
        y = yPosition;
    }

    private Rectangle buildingBase(int x, int y, Color color){
        return new Rectangle(x, y, x+483, y+30, x+483, y+220, x, y+250, color);
    }

    private ArrayList<Rectangle> window(int x, int y, Color color){
        ArrayList<Rectangle> window = new ArrayList<>();
        
        // Add Glass
        window.add(new Rectangle(
            x, 
            y-3, 
            x+55, 
            y, 
            x+55, 
            y+30+3, 
            x, 
            y+30, 
        color));
        
        // Add Frame
        // Vertical Frames
        int frameWidth = 1;
        for (int i = 0; i < 4; i++) {
            window.add(new Rectangle(
                x + (i*55/3), 
                y + i-3, 
                x + frameWidth + (i*55/3), 
                y + i-3, 
                x + frameWidth + (i*55/3), 
                y+33 + i-3, 
                x + (i*55/3), 
                y+33 + i-3, 
            Color.WHITE));
        }
        // Horizontal Frames
        for (int i = 0; i < 3; i++) {
            window.add(new Rectangle(
                x, 
                y + (i*33/2) - 5, 
                x+55, 
                y + (i*33/2),  
                x+55, 
                y + frameWidth + (i*33/2), 
                x, 
                y + frameWidth + (i*33/2) - 5, 
            Color.WHITE));
        }

        return window;
    }

    private ArrayList<Rectangle> firstFloor(int x, int y, Color canteenBg, Color pillars){
        ArrayList<Rectangle> firstFloor = new ArrayList<>();

        // Black Background 
        firstFloor.add(new Rectangle(
            x, 
            y+180, 
            x+483, 
            y+180, 
            x+483, 
            y+220, 
            x, 
            y+250, 
        canteenBg));

        // Pillars
        int pillarWidth = 15;
        for (int i = 0; i < 8; i++){
            if (i==3 || i==4){ // Center pillars are doubled due to the main Gonz entrance arch
                firstFloor.add(new Rectangle(
                    x + i*(483/7) - pillarWidth/2 - 3,
                    y + 180,
                    x + pillarWidth + i*(483/7) - pillarWidth/2 - 3,
                    y + 180,
                    x + pillarWidth + i*(483/7) - pillarWidth/2 - 3,
                    y + 250 - i*(30/7) - 2,
                    x + i*(483/7) - pillarWidth/2 - 3,
                    y + 250 - i*(30/7),
                pillars));
                firstFloor.add(new Rectangle(
                    x + i*(483/7) + pillarWidth/2 + 3,
                    y + 180,
                    x + pillarWidth + i*(483/7) + pillarWidth/2 + 3,
                    y + 180,
                    x + pillarWidth + i*(483/7) + pillarWidth/2 + 3,
                    y + 250 - i*(30/7) - 2,
                    x + i*(483/7) + pillarWidth/2 + 3,
                    y + 250 - i*(30/7),
                pillars));
            } else if (i == 7){ // The final post needs to be adjusted
                firstFloor.add(new Rectangle(
                    x + i*(483/7) - pillarWidth,
                    y + 180,
                    x + i*(483/7),
                    y + 180,
                    x + i*(483/7),
                    y + 250 - i*(30/7) - 2,
                    x + i*(483/7) - pillarWidth,
                    y + 250 - i*(30/7),
                pillars));
            }
            else {
                firstFloor.add(new Rectangle(
                    x + i*(483/7),
                    y + 180,
                    x + pillarWidth + i*(483/7),
                    y + 180,
                    x + pillarWidth + i*(483/7),
                    y + 250 - i*(30/7) - 2,
                    x + i*(483/7),
                    y + 250 - i*(30/7),
                pillars));
            }
        }

        return firstFloor;
    }

    private ArrayList<Rectangle> outerAccent(int x, int y, Color color){
        ArrayList<Rectangle> outerAccent = new ArrayList<>();
        
        // White "hat" of Gonz
        outerAccent.add(new Rectangle(
            x, 
            y, 
            x+483, 
            y+30, 
            x+483, 
            y+66, 
            x, 
            y+30, 
        color));

        // Vertical accents
        int verticalAccentWidth = 11;
        for (int i = 0; i < 7; i++) {
            outerAccent.add(new Rectangle(
                x+57+1+(i*(485/7)), 
                y+10+(i*25/6), 
                x+57+1+(i*(485/7)) + verticalAccentWidth, 
                y+10+(i*25/6), 
                x+57+1+(i*(485/7)) + verticalAccentWidth, 
                y+180, 
                x+57+1+(i*(485/7)), 
                y+180, 
            color));
        }

        // Horizontal Accent
        outerAccent.add(new Rectangle(
            x,
            y+170+5, 
            x+485, 
            y+180-5, 
            x+485, 
            y+180+10-5, 
            x, 
            y+170+10+5, 
        color));

        return outerAccent;
    }

    // Gonzaga Signage
    private Rectangle gonzagaSignage(int x, int y, Color color){
        return new Rectangle(
            x + 200, 
            y + 155,
            x + 300, 
            y + 155, 
            x + 300, 
            y + 175, 
            x + 200, 
            y + 175, 
        color);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Building wall everything is on
        buildingBase(x, y, new Color(201,115,100,opacity)).draw(g2d);

        // First row of windows
        for (int i = 0; i < 7; i++) {
            for (Rectangle windowObject : window(x+1 + (i*(12+57)),y+36 + (i*5), new Color(149,143,144,opacity))) {
                windowObject.draw(g2d);
            }
        }

        // Second row of windows
        for (int i = 0; i < 7; i++) {
            for (Rectangle windowObject : window(x+1 + (i*(12+57)),y+100 + (i*5), new Color(149,143,144,opacity))) {
                windowObject.draw(g2d);
            }
        }

        // First Floor
        for (Rectangle floorElement : firstFloor(x, y, new Color(30,30,30,opacity), new Color(201,115,100,opacity))){
            floorElement.draw(g2d);
        }

        // White accents
        for (Rectangle accent : outerAccent(x, y, new Color(198,187,195,opacity))){
            accent.draw(g2d);
        }

        // Gonzaga Signage
        gonzagaSignage(x, y, new Color(248,201,133,opacity)).draw(g2d);
    }
}