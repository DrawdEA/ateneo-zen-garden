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
    private static final int MAX_LETTERS = 20;
    
    final double[][] PATH_PARAMETERS = {
        // {x,y,scale}
        {-80, 365, 0.3}, // Down Left Gonz Exit connects to [1]
        {205, 290, 0.2}, // Gonz Entrance connects to [0,5]
        {-50, 365, 0.4}, // Straight left Exit connects to [5]
        {530, 285, 0.2}, // Gonz Upper Right Exit connects to [5]
        {875, 400, 0.5}, // Foreground exit connects to [5]
        {640, 365, 0.4}, // Cross Road connects to [1,3,4] (Cross Road last because people cannot spawn here)
    }; 

    final int[][] CONNECTED_PATH = {
        {1},
        {0,5},
        {5},
        {5},
        {5},
        {1,2,3,4},
    };

    final Color[] SHIRT_COLORS = {
        new Color(125,26,5),
        new Color(89,201,241),
        new Color(21,17,82),
        new Color(21,16,80),
        new Color(255,217,33),
        new Color(165,230,186),
        new Color(54,5,104),
        new Color(91,42,134),
        new Color(119,133,172),
        new Color(237,123,132),
        new Color(176,215,255),
        new Color(173,172,181),
        new Color(45,49,66),
        new Color(177,182,149),
    };

    final Color[] SKIN_COLORS = {
        new Color(238,202,138),
        new Color(214,156,86),
        new Color(104,53,15),
        new Color(245,223,145),
        new Color(245,219,203),
        new Color(199,170,116),
    };

    final Color[] PANTS_COLORS = {
        new Color(217,244,255),
        new Color(125,26,5),
        new Color(89,201,241),
        new Color(21,17,82),
        new Color(21,16,80),
        new Color(255,217,33),
        new Color(176,215,255),
        new Color(45,49,66),
    };

    final Color[] GREEN_COLORS = {
        new Color(15, 58, 42), 
        new Color(25, 89, 51),
        new Color(13, 48, 44),
        new Color(23, 112, 73)
    };

    ArrayList<DrawingObject> drawingObjects;
    Timer timer;

    // Laptop commandline related fields
    boolean laptopOpened;
    boolean commandLineOpened;
    String command;
    boolean isBuildingsToggled;

    Random random = new Random();

    int peopleSpawnRate;

    int leafCounter;
    boolean canSpawnLeaves;
    boolean hasLeaves;
    int fallingLeafSpawnRate; // every 100x ms (smallest 1)
    double[][] TREE_PARAMETERS = {
        {500.1, 380.1, 6.1, 0, 6, 2},
        {680.1, 450.1, 7.1, 0, 5, 2},
        {700.1, 600.1, 8.1, 0, 7, 3},
        {-20.1, 600.1, 8.1, 0, 7, 1}
    };

    boolean initializedCanvas = false;
    int peopleSpawnerTimerLoopCounter = 5;

    /**
     * Instantiate a SceneCanvas (an extension of JComponent).
     */
    public SceneCanvas() {
        laptopOpened = false;
        commandLineOpened = true;
        command = "";
        peopleSpawnRate = 2;
        leafCounter = 0;
        canSpawnLeaves = false;
        isBuildingsToggled = false;
        fallingLeafSpawnRate = 5;
        hasLeaves = true;

        drawingObjects = new ArrayList<DrawingObject>();
        ArrayList<DrawingObject> people = new ArrayList<DrawingObject>();

        timer = new Timer(100, e -> {
            // Checks if there is a tree with max growth.
            for (DrawingObject object : drawingObjects) {
                if (object instanceof Tree tree){
                    if (tree.canFallLeaves) { 
                        canSpawnLeaves = true; 
                    };
                }
            }

            // Set up the falling leaves.
            leafCounter++;
            if (leafCounter % fallingLeafSpawnRate == 0 && canSpawnLeaves) {
                drawingObjects.add(new FallingLeaf(random.nextInt(-200, 1000), 0, GREEN_COLORS[random.nextInt(4)], random.nextDouble() / 4, random.nextInt(20), random.nextInt(10, 20)));
            }
            canSpawnLeaves = false;

            if (peopleSpawnerTimerLoopCounter % 40 == 0){ // Every 4 seconds generate a new person
                for (int i = 0; i < peopleSpawnRate; i++) {
                    int spawnPoint = random.nextInt(PATH_PARAMETERS.length - 1); // Since people cannot spawn on the cross road
                    int connectedPathIndex = random.nextInt(CONNECTED_PATH[spawnPoint].length);
                    int connectedPath = CONNECTED_PATH[spawnPoint][connectedPathIndex];
                    Color shirtColor = SHIRT_COLORS[random.nextInt(SHIRT_COLORS.length)];
                    Color skinColor = SKIN_COLORS[random.nextInt(SKIN_COLORS.length)];
                    Color pantsColor = PANTS_COLORS[random.nextInt(PANTS_COLORS.length)];
                    int speed = random.nextInt(5, 8);

                    Person person = new Person(
                        "walking", 
                        shirtColor, pantsColor, skinColor, 
                        (int) PATH_PARAMETERS[spawnPoint][0], (int) PATH_PARAMETERS[spawnPoint][1],
                        (int) PATH_PARAMETERS[connectedPath][0], (int) PATH_PARAMETERS[connectedPath][1],
                        PATH_PARAMETERS[spawnPoint][2], PATH_PARAMETERS[connectedPath][2],
                        speed
                    );

                    // Checks if the timer function is being called in the constructor or in repaint()
                    if (initializedCanvas) { // If in repaint() then insert the new person into the drawingObjects
                        drawingObjects.add(7, person);
                    } else { // if in the constructor then add it to a temporary people object to added at the right later at the end of the constructor
                        people.add(person);
                    }

                    // Set timer loop counter back to 1  
                    peopleSpawnerTimerLoopCounter = 1;
                }
            }
            peopleSpawnerTimerLoopCounter++;
            repaint();
        });
        timer.start();

        // Add the individual drawing objects.
        drawingObjects.add(new Background(0, 0));
        drawingObjects.add(new GonzagaHall(-91.6, 97.3));
        drawingObjects.add(new SchmittHall(574.4,23));
        drawingObjects.add(new Bush(600, 300, 700, 70));
        drawingObjects.add(new Bush(-500, 320, 600, 50));

        drawingObjects.add(new Tree(timer, hasLeaves, TREE_PARAMETERS[0][0], TREE_PARAMETERS[0][1], TREE_PARAMETERS[0][2], (int) TREE_PARAMETERS[0][3], (int) TREE_PARAMETERS[0][4], (int) TREE_PARAMETERS[0][5]));
        drawingObjects.add(new Tree(timer, hasLeaves, TREE_PARAMETERS[1][0], TREE_PARAMETERS[1][1], TREE_PARAMETERS[1][2], (int) TREE_PARAMETERS[1][3], (int) TREE_PARAMETERS[1][4], (int) TREE_PARAMETERS[1][5]));
        
        // People should be added in this layer [7]
        
        drawingObjects.add(new Tree(timer, hasLeaves, TREE_PARAMETERS[2][0], TREE_PARAMETERS[2][1], TREE_PARAMETERS[2][2], (int) TREE_PARAMETERS[2][3], (int) TREE_PARAMETERS[2][4], (int) TREE_PARAMETERS[2][5]));
        drawingObjects.add(new Tree(timer, hasLeaves, TREE_PARAMETERS[3][0], TREE_PARAMETERS[3][1], TREE_PARAMETERS[3][2], (int) TREE_PARAMETERS[3][3], (int) TREE_PARAMETERS[3][4], (int) TREE_PARAMETERS[3][5]));
        
        
        drawingObjects.add(new Bush(-50, 600, 900, 80));
        drawingObjects.add(new Bush(-50, 550, 600, 80));
        drawingObjects.add(new Bush(-50, 500, 350, 150));
        

        drawingObjects.add(new Laptop(250, 400, laptopOpened, commandLineOpened, command));

        for (DrawingObject person : people){
            drawingObjects.add(7, person);
        }

        // Set to true so that we know index 5 exists in drawingObjects
        initializedCanvas = true;

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

    /**
     * Toggles the laptop.
     */
    public void toggleLaptop() {
        laptopOpened = !laptopOpened;
        // Find the Laptop object and update its state
        getLaptop().toggleOpen();

        repaint();
        this.requestFocusInWindow();
    }

    /**
     * Checks for backspace input. Used for the laptop command line interface.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        if (laptopOpened) {
            if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE && command.length() <= MAX_LETTERS && e.getKeyCode() != KeyEvent.VK_ENTER) {
                command += e.getKeyChar();
            }
        }
        
        getLaptop().updateCommand(command);
        repaint();
    }

    /**
     * Checks for enter and letter input. Used for the laptop command line interface.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (laptopOpened) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) { // In case enter, clear out the command and execute it if it belongs to one of the correct ones.
                String output;
                command = command.toLowerCase().strip(); // clean up the command string and make it uniform
                
                // help command to display all accepted commands
                if (command.equals("help")){
                    output = String.format("Below is a list all commands and their flags to change the scenery:\n");
                    // Building commands
                    output += String.format("   bldg --toggle\tToggle the perspective of the buildings\n");
                    // Trees Commands
                    output += String.format("   trees --regrow\tRegrow the trees\n");
                    output += String.format("   leaves --toggle\tToggle all the leaves on the trees\n");
                    output += String.format("   leaves++\t\tMore falling leaves\n");
                    output += String.format("   leaves--\t\tLess falling leaves\n");
                    // People Commands
                    output += String.format("   people++\t\tIncrease the people spawn rate by 1\n");
                    output += String.format("   people--\t\tDecrease the people spawn rate by 1\n");
                    // Music Commands
                    output += String.format("   music --playlist\tIncrease the people spawn rate by 1\n");
                    output += String.format("   music --shuffle\tDecrease the people spawn rate by 1\n");


                // Building Commands
                } else if (command.equals("bldg --toggle")) {
                    isBuildingsToggled = !isBuildingsToggled;
                    output = String.format("Toggled Buildings in the scenery\n");

                    if (isBuildingsToggled){
                        // Set Gonz to the New Gonz
                        drawingObjects.set(1, new PerspectiveGonzagaHall(-30, 120)); 
                        drawingObjects.set(2, new PerspectiveSchmittHall(600, 235)); 
                    } else {
                        drawingObjects.set(1, new GonzagaHall(-91.6, 97.3));
                        drawingObjects.set(2, new SchmittHall(574.4,23));
                    }


                // Trees Commands
                } else if (command.equals("trees --regrow")) {
                    output = String.format("Regrowing all trees in the scenery\n");
                    int treeCounter = 0;
                    for (int i = 0; i < drawingObjects.size(); i++){
                        if (drawingObjects.get(i) instanceof Tree){
                            drawingObjects.set(i, new Tree(timer, hasLeaves, TREE_PARAMETERS[treeCounter][0], TREE_PARAMETERS[treeCounter][1], TREE_PARAMETERS[treeCounter][2], (int) TREE_PARAMETERS[treeCounter][3], (int) TREE_PARAMETERS[treeCounter][4], (int) TREE_PARAMETERS[treeCounter][5]));
                            treeCounter++;
                        }
                    }

                } else if (command.equals("leaves --toggle")) {
                    output = String.format("Stripped all trees of their leaves in the scenery\n");
                    hasLeaves = !hasLeaves;
                    for (DrawingObject object : drawingObjects) {
                        if (object instanceof Tree tree) {
                            tree.toggleLeaves();
                        }
                    }


                // Leaves Commands
                } else if (command.equals("leaves++")) {
                    if (fallingLeafSpawnRate == 1){
                        output = String.format("Falling leaves spawn rate is already at maximum!\n");
                    } else {
                        output = String.format("Increased falling leaves spawn interval to %d\n", fallingLeafSpawnRate);
                        fallingLeafSpawnRate--;
                    }

                } else if (command.equals("leaves--")) {
                    fallingLeafSpawnRate++;
                    output = String.format("Decreased falling leaves spawn interval to %d\n", fallingLeafSpawnRate);


                // People Commands
                } else if (command.equals("people++")) {
                    peopleSpawnRate++;
                    output = String.format("Increased people spawning rate to %d\n", peopleSpawnRate);

                } else if (command.equals("people--")) {
                    if (peopleSpawnRate == 1) {
                        peopleSpawnRate++;
                        output = String.format("People spawning rate is already at its minimum at 1!\n");
                    } else {
                        peopleSpawnRate--;
                        output = String.format("Decreased people spawning rate to %d\n", peopleSpawnRate);
                    }


                // Music Commands
                } else if (command.equals("music --playlist")) {
                    output = String.format("Your current playlist is:\n");
                    output += String.format("  %s", getLaptop().getPlaylist());

                } else if (command.equals("music --shuffle")) {
                    getLaptop().shufflePlaylist();
                    output = String.format("Playlist has been shuffled!");


                // Set warning for unknown commands
                } else {
                    output = String.format("Unknown command: \"%s\"\n",command);
                }

                System.out.println(output);
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

    /**
     * Checks for clicks in the music player.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (laptopOpened) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if (getLaptop().isInCommandLineButton(mouseX, mouseY)) {
                getLaptop().goToMusic(false);
            } else if (getLaptop().isInMusicButton(mouseX, mouseY)) {
                getLaptop().goToMusic(true);   
            }
            if (!getLaptop().isInCommandLineTab()) {
                if (getLaptop().isInPlayButton(mouseX, mouseY)) {
                    getLaptop().toggleMusic();
                } else if (getLaptop().isInLeftButton(mouseX, mouseY)) {
                    getLaptop().playPreviousMusic();
                } else if (getLaptop().isInRightButton(mouseX, mouseY)) {
                    getLaptop().playNextMusic();
                }
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
