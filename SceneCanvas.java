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
       
        // drawingObjects.add(new Triangle(100, 150, 200, 100, 100, 200, Color.BLACK)); // Sample custom shape
        // drawingObjects.add(new Rectangle(100, 160, 170, 80, 300, 300, 400, 380, Color.BLUE)); // Sample custom shape
        drawingObjects.add(new Laptop(100, 100, laptopOpened)); // Sample combination of shape
        drawingObjects.add(new GonzagaHall(0,150));
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

        for (DrawingObject object : drawingObjects) {
            object.draw((Graphics2D) g);
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
