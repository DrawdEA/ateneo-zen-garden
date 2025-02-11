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
        this.setPreferredSize(new Dimension(800, 600));
    }
}
