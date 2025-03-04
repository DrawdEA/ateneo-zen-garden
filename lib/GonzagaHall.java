/**
 * The GonzagaHall class is responsible for setting the Gonzaga Hall building.
 * This includes the main building, windows, signboard, and hallway.
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
import java.io.*;

public class GonzagaHall implements DrawingObject {
    double x;
    double y;

    Rectangle buildingTop;
    Rectangle building;

    Rectangle shedTop;
    Rectangle shed;

    Rectangle signTop;
    Rectangle sign;

    Rectangle curb;

    Rectangle background;
    Rectangle mainPath;

    ArrayList<Rectangle> windows;
    ArrayList<Rectangle> pillars;

    Rectangle shedShadow;
    Rectangle mainShadow;
    Rectangle buildingShadow;

    File avenirFile;
    Font avenir;

    /**
     * Initializes the location and values of the shapes and the font and text needed for the signboard.
     * 
     * @param xPosition x value of the object's anchor point
     * @param yPosition y value of the object's anchor point
     */
    public GonzagaHall(double xPosition, double yPosition) {
        x = xPosition;
        y = yPosition;

        // Get the font.
        try {
            avenirFile = new File("assets/fonts/Avenir/AvenirLTStd-Black.otf");
            avenir = Font.createFont(Font.TRUETYPE_FONT, avenirFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException ex) {
            ex.printStackTrace();
        }

        // Set up the building's framework.
        buildingTop = new Rectangle(x + 4.5, y, 587.2, 5, new Color(181, 133, 68));
        building = new Rectangle(x + 4.5, y + 5, 587.2, 181.2, new Color(238, 202, 138));

        shedTop = new Rectangle(x, y + 166, 596.2, 5, new Color(181, 133, 68));
        shed = new Rectangle(x, y + 166, 596.2, 21.2, new Color(238, 202, 138));

        curb = new Rectangle(x, y + 240.1, 596.2, 8.1, new Color(211, 203, 182));
        mainPath = new Rectangle(x + 279.3, y + 240, 57.9, 8.9, new Color(48, 48, 48));
        background = new Rectangle(x, y + 187.2, 596.2, 59.5, new Color(48, 48, 48));

        signTop = new Rectangle(x + 251.2, y + 150, 123.6, 5, new Color(181, 133, 68));
        sign = new Rectangle(x + 251.2, y + 155, 123.6, 11, new Color(221, 90, 48));

        // Set up the windows.
        windows = new ArrayList<Rectangle>();
        for (int i = 0; i < 5; i++) {
            windows.add(new Rectangle(x + 25.8 + i * 113, y + 24.8, 86.5, 41.2, new Color(148, 212, 228)));
            windows.add(new Rectangle(x + 25.8 + i * 113, y + 66, 86.5, 25.9, new Color(221, 90, 48)));
            windows.add(new Rectangle(x + 25.8 + i * 113, y + 66, 86.5, 5, new Color(181, 133, 68)));

            windows.add(new Rectangle(x + 25.8 + i * 113, y + 24.8 + 80, 86.5, 41.2, new Color(148, 212, 228)));
            windows.add(new Rectangle(x + 25.8 + i * 113, y + 66 + 80, 86.5, 25.9, new Color(221, 90, 48)));
            windows.add(new Rectangle(x + 25.8 + i * 113, y + 66 + 80, 86.5, 5, new Color(181, 133, 68)));
        }

        // Set up the pillars.
        pillars = new ArrayList<Rectangle>();
        for (int i = 0; i < 6; i++) {
            pillars.add(new Rectangle(x + i * 116.46, y + 187.5, 13.9, 52.8, new Color(221, 90, 48)));
        }

        // Set up the shadows.
        shedShadow = new Rectangle(x + 251.2, y + 150, 123.6, 16, new Color(0, 0, 0, 0.33f));
        mainShadow = new Rectangle(x, y + 187.2, 596.2, 53.3, new Color(0, 0, 0, 0.33f));
        buildingShadow = new Rectangle(x + 0, y + 240.5, x + 596.2, y + 240.5, x + 640.8, y + 331.7, x + 44.9, y + 331.7, new Color(0, 0, 0, 0.33f));
        
    }

    /**
     * Draws all of the objects in the correct order.
     */
    public void draw(Graphics2D g2d) {
        building.draw(g2d);
        buildingTop.draw(g2d);
        background.draw(g2d);
        curb.draw(g2d);
        mainPath.draw(g2d);
        for (Rectangle windowPart : windows) { windowPart.draw(g2d); }
        for (Rectangle pillarPart : pillars) { pillarPart.draw(g2d); }
        shed.draw(g2d);
        shedTop.draw(g2d);
        signTop.draw(g2d);
        sign.draw(g2d);
        g2d.setColor(Color.WHITE);
        g2d.setFont(avenir.deriveFont(Font.BOLD, 12f));
        g2d.drawString("GONZAGA HALL", (int) (x + 265), (int) (y + 165));
        shedShadow.draw(g2d);
        mainShadow.draw(g2d);
        buildingShadow.draw(g2d);
    }
}
