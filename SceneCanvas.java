import java.awt.*;
import java.util.*;
import javax.swing.*;

public class SceneCanvas extends JComponent {
    ArrayList<DrawingObject> drawingObjects; 

    /**
     * Instantiate a SceneCanvas (an extension of JComponent).
     */
    public SceneCanvas() {
        drawingObjects = new ArrayList<DrawingObject>();

        // Add the DrawingObjects in the array list.
        drawingObjects.add(new Circle()); // Sample shape
        drawingObjects.add(new Line()); // Sample shape
        drawingObjects.add(new Square()); // Sample shape

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
        for (DrawingObject object : drawingObjects) {
            object.draw((Graphics2D) g);
        }
    }
}
