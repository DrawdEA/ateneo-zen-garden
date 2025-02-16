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
import java.util.*;
import javax.swing.*;

public class SceneCanvas extends JComponent {
    ArrayList<DrawingObject> drawingObjects;
    boolean laptopOpened;

    /**
     * Instantiate a SceneCanvas (an extension of JComponent).
     */
    public SceneCanvas() {
        drawingObjects = new ArrayList<DrawingObject>();
       
        drawingObjects.add(new Laptop(100, 100, laptopOpened)); // Sample combination of shape
        drawingObjects.add(new GonzagaHall(0,150));
        drawingObjects.add(new SchmittHall(575,100));
        // Set up miscellaneous details.
        this.setPreferredSize(new Dimension(800, 600));
    }

    /**
     * Draws every shape in the drawingObjects array list.
     * 
     * @param g main graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("./assets/images/zen.png").getImage(),0,0,this);

        // Cast Graphics to Graphics2D and apply anti-aliasing key 
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2d.setRenderingHints(rh);

        for (DrawingObject object : drawingObjects) {
            object.draw(g2d);
        }
    }

    public void toggleLaptop() {
        laptopOpened = !laptopOpened;
        // Find the Laptop object and update its state
        for (DrawingObject object : drawingObjects) {
            if (object instanceof Laptop) {
                ((Laptop) object).toggleOpen();
            }
        }
        repaint();
    }
}
