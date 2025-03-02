/**
 * The SceneCanvas class is an extension of JComponent that acts as the canvas for all of the drawings.
 * It also handles the input within it, such as the music player and the command line interface.
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
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;


public class SceneCanvas extends JComponent implements KeyListener, MouseListener {
    private static final int MAX_LETTERS = 14;

    ArrayList<DrawingObject> drawingObjects;
    boolean laptopOpened;
    boolean commandLineOpened;
    String command;
    Timer timer;

    Random random = new Random();

    int peopleSpawnRate;
    final double[][] PATH_PARAMETERS = {
        // {x,y,scale}
        {-75, 365, 0.4}, // Down Left Gonz Exit connects to [1]
        {205, 290, 0.2}, // Gonz Entrance connects to [0,2]
        {640, 365, 0.4}, // Cross Road connects to [1,3,4]
        {-50, 365, 0.4}, // Straight left Exit connects to [2]
        {530, 285, 0.2}, // Gonz Upper Right Exit connects to [2]
        {875, 400, 0.5}, // Foreground exit connects to [2]
    }; 

    final int[][] CONNECTED_PATH = {
        {1},
        {0,2},
        {1,3,4},
        {2},
        {2},
        {2},
    };

    final Color[] SHIRT_COLORS = {
        new Color(125,26,5)
    };

    final Color[] SKIN_COLORS = {
        new Color(238,202,138)
    };

    final Color[] PANTS_COLORS = {
        new Color(217,244,255)
    };

    /**
     * Instantiate a SceneCanvas (an extension of JComponent).
     */
    public SceneCanvas() {
        laptopOpened = false;
        commandLineOpened = true;
        command = "";
        peopleSpawnRate = 1;

        drawingObjects = new ArrayList<DrawingObject>();

        // Add the individual drawing objects.
        drawingObjects.add(new Background(0, 0));
        drawingObjects.add(new GonzagaHall(-91.6, 97.3));
        drawingObjects.add(new SchmittHall(574.4,23));

        // drawingObjects.add(new Tree(timer, 680.1, 450.1, 7.1, 0, 5, 2));
        // drawingObjects.add(new Tree(timer, 700.1, 600.1, 8.1, 0, 7, 3));
        // drawingObjects.add(new Tree(timer, -20.1, 600.1, 8.1, 0, 7, 1));
        // drawingObjects.add(new Tree(timer, 500.1, 380.1, 6.1, 0, 6, 2));
        
        // // To the right
        // drawingObjects.add(new Person("walking", new Color(125,26,5), new Color(217,244,255), new Color(238,202,138), 0, 365, 640, 365, 0.4, 0.4, 15));
        // drawingObjects.add(new Person("walking", new Color(125,26,5), new Color(217,244,255), new Color(238,202,138), 205, 290, 640, 365, 0.2, 0.4, 15));
        
        // // To the left
        // drawingObjects.add(new Person("walking", new Color(125,26,5), new Color(217,244,255), new Color(238,202,138), 640, 365, 0, 365, 0.4, 0.4, 15));
        // drawingObjects.add(new Person("walking", new Color(125,26,5), new Color(217,244,255), new Color(238,202,138), 640, 365, 205, 290, 0.4, 0.2, 15)); // Cross road to gonz entrance
        // drawingObjects.add(new Person("walking", new Color(125,26,5), new Color(217,244,255), new Color(238,202,138), 205, 290, -130, 365, 0.2, 0.4, 30));
        // drawingObjects.add(new Person("walking", new Color(125,26,5), new Color(217,244,255), new Color(238,202,138), 640, 365, 530, 285, 0.4, 0.2, 10)); // Crossroad to gonz side exit
        
        // drawingObjects.add(new Person("walking", new Color(0,0,0), new Color(217,244,255), new Color(238,202,138), 875, 400, 640, 365, 0.5, 0.4, 15)); // foreground to cross
        
        // Standing still
        // drawingObjects.add(new Person("idling", new Color(125,26,5), new Color(217,244,255), new Color(238,202,138), 205, 290, 0.2)); // Entrance of Gonz
        // drawingObjects.add(new Person("idling", new Color(125,26,5), new Color(217,244,255), new Color(238,202,138), 0, 365, 0.4)); // Left Entrance
        // drawingObjects.add(new Person("idling", new Color(125,26,5), new Color(217,244,255), new Color(238,202,138), 640, 365, 0.4)); // Right Cross Road
        // drawingObjects.add(new Person("idling", new Color(125,26,5), new Color(217,244,255), new Color(238,202,138), 530, 285, 0.2)); // Gonz Side exit Road

        drawingObjects.add(new Bush(-50, 520, 350, 150));
        drawingObjects.add(new Laptop(250, 400, laptopOpened, commandLineOpened, command));
        
        // Add timer object to continuously update the drawings.
        timer = new Timer(500, e -> {
            for (int i = 0; i < peopleSpawnRate; i++) {
                int spawnPoint = random.nextInt(PATH_PARAMETERS.length);
                int connectedPath = random.nextInt(CONNECTED_PATH[spawnPoint].length);
                Color shirtColor = SHIRT_COLORS[random.nextInt(SHIRT_COLORS.length)];
                Color skinColor = SKIN_COLORS[random.nextInt(SKIN_COLORS.length)];
                Color pantsColor = PANTS_COLORS[random.nextInt(PANTS_COLORS.length)];
                int speed = random.nextInt(10, 30);

                drawingObjects.add(new Person(
                    "walking", 
                    shirtColor, pantsColor, skinColor, 
                    (int) PATH_PARAMETERS[spawnPoint][0], (int) PATH_PARAMETERS[spawnPoint][1],
                    (int) PATH_PARAMETERS[connectedPath][0], (int) PATH_PARAMETERS[connectedPath][1],
                    PATH_PARAMETERS[spawnPoint][2], PATH_PARAMETERS[connectedPath][2],
                    speed
                ));
            }
            repaint();
        });
        timer.start();

        // Set up miscellaneous details.
        this.setFocusable(true);
        this.addKeyListener(this); 
        this.addMouseListener(this);
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
        // Cast Graphics to Graphics2D and apply anti-aliasing key.
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2d.setRenderingHints(rh);

        // Draw every object.
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

    // Checks for backspace input. Used for the laptop command line interface.
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

    // Checks for enter and letter input. Used for the laptop command line interface.
    @Override
    public void keyPressed(KeyEvent e) {
        if (laptopOpened) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) { // In case enter, clear out the command and execute it if it belongs to one of the correct ones.
                // TODO: add commands in case if it corresponds to a something.
                    
                switch (command) {
                    case ("spawn --cats"):
                        // TODO: add command
                    default:
                        System.out.println(command);
                }

                command = "";
            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) { // Removes a letter if user typed backspace.
                if (command.length() > 0) {
                    command = command.substring(0, command.length() - 1); 
                }
            }

            getLaptop().updateCommand(command);
            repaint();
        }
    }

    // Checks for clicks in the music player.
    @Override
    public void mouseClicked(MouseEvent e) {
        if (laptopOpened) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if (getLaptop().isInCommandLineButton(mouseX, mouseY)) {
                getLaptop().goToMusic(false);
            } else if (getLaptop().isInMusicButton(mouseX, mouseY)) {
                getLaptop().goToMusic(true);
            } else if (getLaptop().isInPlayButton(mouseX, mouseY)) {
                getLaptop().toggleMusic();
            } else if (getLaptop().isInLeftButton(mouseX, mouseY)) {
                getLaptop().playPreviousMusic();
            } else if (getLaptop().isInRightButton(mouseX, mouseY)) {
                getLaptop().playNextMusic();
            }

            repaint();
        }
    }

    // Unused interface methods.
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
