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
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.Timer;

public class Person implements DrawingObject{
    Timer timer;

    String state; // This can be "walking", "idling", or "crawling" (maybe make this an enum)
    int animationFrameNum;
    Color shirtColor;
    Color skinColor;
    Color pantsColor;

    // The variables with 1 is the initial state then x2 is the final destination for walking states
    int x1, x2;
    int y1, y2;
    float scale1, scale2;

    // These are the actual current values of the person
    int x,y;
    float scale;

    // Constructor to create walking people
    public Person(String state, Color shirtColor, Color pantsColor, Color skinColor, int x1, int x2, int y1, int y2, float scale1, float scale2){
        this.state = state;
        this.shirtColor = shirtColor;
        this.skinColor = skinColor;
        this.pantsColor = pantsColor;

        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.scale1 = scale1;
        this.scale2 = scale2;

        x = x1;
        y = y1;
        scale = scale1;

        animationFrameNum = 0;
        timer = new Timer(300, e -> {
            incrementAnimation();
        });
        timer.start();
    }

    // Constructor to create idling people
    public Person(String state, Color shirtColor, Color pantsColor,  Color skinColor, int x1, int y1, float scale1){
        this.state = state;
        this.shirtColor = shirtColor;
        this.skinColor = skinColor;
        this.pantsColor = pantsColor;

        this.x1 = x1;
        this.y1 = y1;
        this.scale1 = scale1;

        x = x1;
        y = y1;
        scale = scale1;

        animationFrameNum = 0;
        timer = new Timer(750, e -> {
            incrementAnimation();
        });
        timer.start();
    }

    private ArrayList<Rectangle> getWalkingAnimationFrame(){
        ArrayList<Rectangle> walkingFrame = new ArrayList<>();
        int frameNum = animationFrameNum % 10;


        return walkingFrame;
    }

    public void incrementAnimation(){
        animationFrameNum++;
    }

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform originalTransform = g2d.getTransform();

        AffineTransform transform = new AffineTransform();
        g2d.setTransform(transform);

        if (state.equals("walking")){
            getWalkingAnimationFrame();
        } else if (state.equals("idling")){
            if (animationFrameNum % 2 == 0){
                // IDLE FRAME 1
                // Head
                (new Rectangle(x+19.3*scale, y+0*scale, 74*scale, 74*scale, skinColor)).draw(g2d);

                // Right Hand
                transform.setToRotation(Math.toRadians(-6.7), x+82.3*scale, y+92.7*scale);
                g2d.setTransform(transform);
                (new Rectangle(x+82.3*scale, y+92.7*scale, 20.2*scale, 88.8*scale, skinColor)).draw(g2d);
                transform.setToIdentity();
                g2d.setTransform(transform);
                
                // Left Leg
                transform.setToRotation(Math.toRadians(3.3), x+21.5*scale, y+165.9*scale);
                g2d.setTransform(transform);
                (new Rectangle(x+21.5*scale, y+165.9*scale, 21.2*scale, 117.7*scale, pantsColor)).draw(g2d);
                transform.setToIdentity();
                g2d.setTransform(transform);

                // Right Leg
                transform.setToRotation(Math.toRadians(-4.2), x+67.2*scale, y+168.7*scale);
                g2d.setTransform(transform);
                (new Rectangle(x+67.2*scale, y+168.7*scale, 21.2*scale, 117.7*scale, pantsColor)).draw(g2d);
                transform.setToIdentity();
                g2d.setTransform(transform);

                // Shirt
                (new Rectangle(x+19.3*scale, y+85.7*scale, 74.1*scale, 116.9*scale, shirtColor)).draw(g2d);

                // Left Hand
                transform.setToRotation(Math.toRadians(5), x+8.1*scale, y+88.8*scale);
                g2d.setTransform(transform);
                (new Rectangle(x+8.1*scale, y+88.8*scale, 20.6*scale, 92.2*scale, skinColor)).draw(g2d);
                transform.setToIdentity();
                g2d.setTransform(transform);
            } else {
                // IDLE FRAME 2
                // Head
                (new Rectangle(x+19.3*scale, y+0*scale, 74.1*scale, 73.7*scale, skinColor)).draw(g2d);

                // Right Hand
                transform.setToRotation(Math.toRadians(-6.7), x+82.3*scale, y+85*scale);
                g2d.setTransform(transform);
                (new Rectangle(x+82.3*scale, y+85*scale, 20.2*scale, 88.8*scale, skinColor)).draw(g2d);
                transform.setToIdentity();
                g2d.setTransform(transform);
                
                 // Left Leg
                transform.setToRotation(Math.toRadians(5.8), x+24.1*scale, y+164*scale);
                g2d.setTransform(transform);
                (new Rectangle(x+24.1*scale, y+164*scale, 21.2*scale, 117.7*scale, pantsColor)).draw(g2d);
                transform.setToIdentity();
                g2d.setTransform(transform);

                // Right Leg
                transform.setToRotation(Math.toRadians(-6.4), x+65.2*scale, y+167.7*scale);
                g2d.setTransform(transform);
                (new Rectangle(x+65.2*scale, y+167.7*scale, 21.2*scale, 117.7*scale, pantsColor)).draw(g2d);
                transform.setToIdentity();
                g2d.setTransform(transform);

                // Shirt
                (new Rectangle(x+19.3*scale, y+78*scale, 74.1*scale, 116.9*scale, shirtColor)).draw(g2d);

                // Left Hand
                transform.setToRotation(Math.toRadians(5), x+8.1*scale, y+81.2*scale);
                g2d.setTransform(transform);
                (new Rectangle(x+8.1*scale, y+81.2*scale, 20.6*scale, 92.2*scale, skinColor)).draw(g2d);
                transform.setToIdentity();
                g2d.setTransform(transform);


            }
        }

        g2d.setTransform(originalTransform);
    }



} 