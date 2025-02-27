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

 package lib;
 import java.awt.*;
 import java.awt.geom.*;
 import java.util.*;
 import java.util.function.*;
 import javax.swing.Timer;
 
 public class Tree implements DrawingObject {
    private static final Color[] GREEN_COLORS = {
        new Color(15, 58, 42), 
        new Color(25, 89, 51),
        new Color(13, 48, 44),
        new Color(23, 112, 73)
    };
    private final Random random = new Random();
    private static final String[] TREE_PATTERNS = {
        "F+[[X]-X]-F[-FX]+X", // ORIGINAL TREE
        "F-[[X]+X]+F[+FX]-X", // ALTERNATIVE PATTERN TREE
        "F[+X][-X]F[-X]+X" // EQUALLY DISTRIBUTED TREE
    };

    Double x;
    Double y;
    int iterations;
    int maxGrowth;

    Map<String, String> rules;
    Map<String, Consumer<Graphics2D>> instructions;

    String word;

    Double length;

    Deque<AffineTransform> transforms;

    Timer timer;

    ArrayList<Integer> leafLengths;
    ArrayList<Integer> leafHeights;
    ArrayList<Integer> leafColors;

    int leafCounter = 0;



    private String generateLSystem(int layers, String givenWord) {
        for (int i = 0; i < layers - 1; i++) {
            StringBuilder nextGen = new StringBuilder();

            for (int j = 0; j < givenWord.length(); j++) {
                String letter = String.valueOf(givenWord.charAt(j));
                if (rules.containsKey(letter)) {
                    nextGen.append(rules.get(letter));
                } else {
                    nextGen.append(letter);
                }
            }
            
            givenWord = nextGen.toString(); 
        }

        return givenWord;
    }

    public Tree(Double xPosition, Double yPosition, Double len, int startingGrowth, int max, int chosenPattern) {
        x = xPosition;
        y = yPosition;
        iterations = startingGrowth;
        length = len;
        maxGrowth = max;
        word = "-X";

        // Set a timer for growth
        timer = new Timer(1000, e -> {
            if (iterations < maxGrowth) {
                if (random.nextInt(3) == 1) {
                    iterations++;
                }
            }
        });
        timer.start();

        // Create the set of rules for each generation.
        rules = new HashMap<>();
        rules.put("X", TREE_PATTERNS[chosenPattern - 1]);  
        rules.put("F", "FF");

        // Set up the transforms.
        transforms = new ArrayDeque<>(); 

        // Input the set of instructions per character.
        instructions = new HashMap<>();
        instructions.put("F", g2d -> {
            Line line = new Line(0.1, 0.1, 0.1, 0.1 - length, iterations, new Color(139, 69, 19));
            line.draw(g2d);
            g2d.translate(0, -length);
        });
        instructions.put("+", g2d -> {
            g2d.rotate(Math.toRadians(25));
        });
        instructions.put("-", g2d -> {
            g2d.rotate(Math.toRadians(-25));
        });
        instructions.put("[", g2d -> {
            transforms.push(g2d.getTransform());
        });
        instructions.put("]", g2d -> {
            Ellipse leaf = new Ellipse(-3, -3, (iterations - 1) * (5 + leafLengths.get(leafCounter)), (iterations - 1) * (2 + leafHeights.get(leafCounter)), GREEN_COLORS[leafColors.get(leafCounter)]);
            leafCounter++;
            leaf.draw(g2d);
            g2d.setTransform(transforms.pop());
        });

        // Generate the leaf data.
        leafLengths = new ArrayList<Integer>();
        leafHeights = new ArrayList<Integer>();
        leafColors = new ArrayList<Integer>();

        for (int j = 0; j < generateLSystem(max, word).replaceAll("[^\\]]", "").length(); j++) {
            leafLengths.add(random.nextInt(6));
            leafHeights.add(random.nextInt(6));
            leafColors.add(random.nextInt(4));
        }
    }
     
    @Override
    public void draw(Graphics2D g2d) {
        // Generate the rules.
        String ruleString = generateLSystem(iterations, word);

        // Draw the rules.
        AffineTransform originalTransform = g2d.getTransform();
        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(25));
        for (int i = 0; i < ruleString.length(); i++) {
            String letter = String.valueOf(ruleString.charAt(i));
            if (instructions.containsKey(letter)) {
                instructions.get(letter).accept(g2d);
            }
        }
        g2d.setTransform(originalTransform);

        leafCounter = 0;
    }

    
 }
 