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
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class SceneCanvas extends JComponent implements KeyListener {
    private static final int MAX_LETTERS = 20;

    ArrayList<DrawingObject> drawingObjects;
    boolean laptopOpened;
    String command;

    /**
     * Instantiate a SceneCanvas (an extension of JComponent).
     */
    public SceneCanvas() {
        laptopOpened = false;
        command = "";

        drawingObjects = new ArrayList<DrawingObject>();
       
        
        drawingObjects.add(new GonzagaHall(0,150));
        drawingObjects.add(new SchmittHall(575,100));
        drawingObjects.add(new Laptop(250, 400, laptopOpened, command));

        // Set up miscellaneous details.
        this.setFocusable(true);
        this.addKeyListener(this); 
        this.setPreferredSize(new Dimension(800, 600));
        this.requestFocusInWindow();
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

    /**
     * Returns the first laptop object found in the array list.
     * 
     * @return the first laptop object
     */
    public Laptop getLaptop() {
        for (DrawingObject object : drawingObjects) {
            if (object instanceof Laptop) {
                return (Laptop) object;
            }
        }

        return null;
    }

    // Methods for the laptop.
    public void toggleLaptop() {
        laptopOpened = !laptopOpened;
        // Find the Laptop object and update its state
        getLaptop().toggleOpen();

        repaint();
        this.requestFocusInWindow();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (laptopOpened) {
            if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE && command.length() <= MAX_LETTERS) {
                command += e.getKeyChar();
            }
        }
        
        getLaptop().updateCommand(command);
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (laptopOpened) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ENTER: // In case enter, clear out the command and execute it if it belongs to one of the correct ones.
                    
                    
                    // TODO: add commands in case if it corresponds to a something.
                    System.out.println(command);

                    command = "";
                    break;
                case KeyEvent.VK_BACK_SPACE: // Removes a letter if user typed backspace.
                    
                    if (command.length() > 0) {
                        command = command.substring(0, command.length() - 1); 
                    }
                    break;
            }

            getLaptop().updateCommand(command);
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
