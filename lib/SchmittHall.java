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
import java.util.ArrayList;

public class SchmittHall implements DrawingObject {
    int x,y;
    int opacity = 255;

    public SchmittHall(int xPosition, int yPosition){
        x = xPosition;
        y = yPosition;
    }

    private Rectangle buildingBase(int x, int y, Color color) {
        return new Rectangle(
            x, 
            y,
            x + 300, 
            y - 25, 
            x + 300, 
            y + 100, 
            x, 
            y + 115, 
        color);
    }

    private Rectangle roof(int x, int y, Color color) {
        return new Rectangle(
            x,
            y,
            x + 85,
            y - 20,
            x + 130,
            y - 30,
            x + 300,
            y - 25,
        color);
    }

    private ArrayList<Rectangle> window(int x, int y, Color color){
        ArrayList<Rectangle> window = new ArrayList<Rectangle>();
        // Window glass
        window.add(
            new Rectangle(
                x,
                y,
                x + 20,
                y - 2,
                x + 20,
                y + 30 - 4,
                x,
                y + 30,
            color)
        );

        // Window Frame
        // Vertical Frames
        int frameWidth = 1;
        for (int i = 0; i < 3; i++) {
            window.add(new Rectangle(
                x + (i*20/2), 
                y - i*2, 
                x + frameWidth + (i*20/2), 
                y - i*2, 
                x + frameWidth + (i*20/2), 
                y + 30 - i*2, 
                x + (i*20/2), 
                y + 30 - i*2, 
            Color.WHITE));
        }
        // Horizontal Frames
        for (int i = 0; i < 3; i++) {
            window.add(new Rectangle(
                x, 
                y + (i*30/2), 
                x + 20, 
                y + (i*30/2) - i/2 - 4,  
                x + 20, 
                y + frameWidth + (i*30/2) - i/2 - 4, 
                x, 
                y + frameWidth + (i*30/2), 
            Color.WHITE));
        }

        return window;
    }

    private Rectangle accent(int x, int y, Color color){
        return new Rectangle(
            x,
            y,
            x + 5,
            y - 3,
            x + 5,
            y + 85 - 3,
            x,
            y + 85,
        color);
    }

    @Override
    public void draw(Graphics2D g2d) {
        buildingBase(x, y, new Color(255, 254, 224, opacity)).draw(g2d);

        accent(x + 85, y-10, new Color(201,115,100,opacity)).draw(g2d);
        accent(x + 93, y-10, new Color(201,115,100,opacity)).draw(g2d);
        
        roof(x, y, new Color(203, 65, 84, opacity)).draw(g2d);

        // Windows
        for (int i = 0; i < 5; i++) {
            for (Rectangle window : window(x + 20 + (i*40), y + 20 - (i*3), new Color(51,51,51,opacity))){
                window.draw(g2d);
            }

            for (Rectangle window : window(x + 20 + (i*40), y + 20 + 50 - (i*3), new Color(51,51,51,opacity))){
                window.draw(g2d);
            }
        }
    
    }

}