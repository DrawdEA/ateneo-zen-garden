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
 
 public class Tree implements DrawingObject {
    Double x;
    Double y;
    int iterations;

    Map<String, String> rules;
    Map<String, Runnable> instructions;

    String word;


    public Tree(Double xPosition, Double yPosition, int i) {
        x = xPosition;
        y = yPosition;
        iterations = i;

        word = "-X";

        rules = new HashMap<>();
        rules.put("X", "F+[[X]-X]-F[-FX]+X");
        rules.put("F", "FF");

        instructions = new HashMap<>();
        instructions.put("K", () -> {

        });
        instructions.put("X", () -> {

        });
        instructions.put("+", () -> {

        });
        instructions.put("-", () -> {

        });
        instructions.put("[", () -> {

        });
        instructions.put("]", () -> {

        });
    }
     
    @Override
    public void draw(Graphics2D g2d) {
        for (int i = 0; i < iterations; i++) {
            StringBuilder nextGen = new StringBuilder();

            for (int j = 0; j < word.length(); j++) {
                String letter = String.valueOf(word.charAt(j));
                if (rules.containsKey(letter)) {
                    nextGen.append(rules.get(letter));
                } else {
                    nextGen.append(letter);
                }
            }
            
            word = nextGen.toString(); 
            System.out.printf("GENERATION %d: %s\n", i + 1, word);
        }   

        word = "-X";
        System.out.println("DONE");      
    }
 }
 