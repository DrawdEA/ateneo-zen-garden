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
        drawingObjects.add(new Circle(50, 50, 100, Color.PINK)); // Sample shape
        drawingObjects.add(new Circle(50, 150, 100, Color.PINK)); // Sample shape
        drawingObjects.add(new Line(450, 150, 500, 150, 10.0f, Color.BLACK)); // Sample shape
        drawingObjects.add(new Line(450, 120, 500, 100, 100.0f, Color.BLACK)); // Sample shape
        drawingObjects.add(new Line(450, 170, 500, 200, 10.0f, Color.BLACK)); // Sample shape
        drawingObjects.add(new Square(75, 100, 75, Color.PINK)); // Sample shape
        drawingObjects.add(new Square(150, 100, 75, Color.PINK)); // Sample shape
        drawingObjects.add(new Square(225, 100, 75, Color.PINK)); // Sample shape
        drawingObjects.add(new Square(300, 100, 75, Color.PINK)); // Sample shape
        drawingObjects.add(new Circle(350, 100, 75, Color.PINK)); // Sample shape

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
