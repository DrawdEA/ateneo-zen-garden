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

public class SchmittHall implements DrawingObject {
    int x,y;
    int opacity = 100;

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
            y + 115, 
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

    private Rectangle window(int x, int y, Color color){
        
    }

    @Override
    public void draw(Graphics2D g2d) {
        buildingBase(x, y, new Color(255, 254, 224, opacity)).draw(g2d);
        roof(x, y, new Color(203, 65, 84, opacity)).draw(g2d);
    }

}