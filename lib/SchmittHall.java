/**
 * The SchmittHall class is responsible for setting the Schmitt Hall building.
 * This includes the main building, windows, and its roof.
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
import java.util.*;

public class SchmittHall implements DrawingObject {

    double x;
    double y;

    Rectangle mainBuilding;
    Rectangle mainBuildingTop;

    Rectangle roof;
    Rectangle roofTop;

    Rectangle mainShadow;

    ArrayList<Rectangle> windows;

    /**
     * Initializes the location and values of the shapes.
     * 
     * @param xPosition x value of the object's anchor point
     * @param yPosition y value of the object's anchor point
     */
    public SchmittHall(double xPosition, double yPosition) {
        x = xPosition;
        y = yPosition;

        // Set up the roof of the building.
        roofTop = new Rectangle(x, y, 285.7, 5, new Color(181, 133, 68));
        roof = new Rectangle(x, y, 285.7, 21.2, new Color(238, 202, 138));

        // Set up the main framework of the building.
        mainBuildingTop = new Rectangle(x + 5.4, y + 21, 274.8, 5, new Color(181, 133, 68));
        mainBuilding = new Rectangle(x + 5.4, y + 21, 274.8, 294.2, new Color(238, 202, 138));

        // Set up the windows of the building.
        windows = new ArrayList<Rectangle>();
        for (int i = 0; i < 3; i++) {
            windows.add(new Rectangle(x + 19.6 + 85 * i, y + 41.1, 73, 37.5, new Color(48, 48, 48)));
            windows.add(new Rectangle(x + 19.6 + 85 * i, y + 41.1  + 65.3, 73, 37.5, new Color(48, 48, 48)));
            windows.add(new Rectangle(x + 19.6 + 85 * i, y + 41.1  + 65.3 * 2, 73, 37.5, new Color(48, 48, 48)));
        }
        
        // Set up the shadow of the building.
        mainShadow = new Rectangle(x + 5.4, y + 315.2, x + 280.2, y + 315.2, x + 340, y + 426.3, x + 65.5, y + 426.3, new Color(0, 0, 0, 0.33f));
        
    }

    /**
     * Draws all of the objects in the correct order.
     */
    public void draw(Graphics2D g2d) {
        roof.draw(g2d);
        mainBuilding.draw(g2d);
        roofTop.draw(g2d);
        mainBuildingTop.draw(g2d);
        for ( Rectangle window : windows) {
            window.draw(g2d);
        }
        mainShadow.draw(g2d);
    }
}
