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

    private void makeHead(Graphics2D g2d, double headX, double headY, double width){
        (new Rectangle(x+headX*scale, y+headY*scale, width*scale, width*scale, skinColor)).draw(g2d);
    }

    private void makeLimb(Graphics2D g2d, double armX, double armY, double degreeRotation, double width, double height, Color color){
        AffineTransform transform = new AffineTransform();
        transform.setToRotation(Math.toRadians(degreeRotation), x+armX*scale, y+armY*scale);
        g2d.setTransform(transform);
        (new Rectangle(x+armX*scale, y+armY*scale, width*scale, height*scale, color)).draw(g2d);
        transform.setToIdentity();
        g2d.setTransform(transform);
    }

    private void makeShirt(Graphics2D g2d, double shirtX, double shirtY, double width, double height){
        (new Rectangle(x+shirtX*scale, y+shirtY*scale, width*scale, height*scale, shirtColor)).draw(g2d);
    }

    private void getIdleFrame(Graphics2D g2d){
        AffineTransform transform = new AffineTransform();
        g2d.setTransform(transform);

        if (animationFrameNum % 2 == 0){
            // IDLE FRAME 1
            makeHead(g2d, 19.3, 0, 74); // Head
            makeLimb(g2d, 82.3, 92.7, -6.7, 20.2, 88.8, skinColor); // Right Arm
            makeLimb(g2d, 21.5, 165.9, 3.3, 21.5, 117.7, pantsColor); // Left Leg
            makeLimb(g2d, 67.2, 168.7, -4.2, 21.2, 117.7, pantsColor); // Right Leg
            makeShirt(g2d, 19.3, 85.7, 74.1, 116.9); // Shirt
            makeLimb(g2d, 8.1, 88.8, 5, 20.6, 92.2, skinColor); // Left Arm
        } else {
            // IDLE FRAME 2
            makeHead(g2d, 19.3, 0, 73.7); // Head
            makeLimb(g2d, 82.3, 85, -6.7, 20.2, 88.8, skinColor); // Right Arm
            makeLimb(g2d, 24.1, 164, 5.8, 21.2, 117.7, pantsColor); // Left Leg
            makeLimb(g2d, 65.2, 167.7, -6.4, 21.2, 117.7, pantsColor); // Right Leg
            makeShirt(g2d, 19.3, 78, 74.1, 116.9); // Shirt
            makeLimb(g2d, 8.1, 81.2, 5, 20.6, 92.2, skinColor); // Left Arm
        }
    }

    private void getWalkingFrame(Graphics2D g2d){
        int frameNum = animationFrameNum % 10;
        switch (frameNum) {
            case 0:
                // Head

                break;
            default:
                throw new AssertionError();
        }
    }

    public void incrementAnimation(){
        animationFrameNum++;
    }

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform originalTransform = g2d.getTransform();

        if (state.equals("walking")){
            getWalkingFrame(g2d);
        } else if (state.equals("idling")){
            getIdleFrame(g2d);
        }

        g2d.setTransform(originalTransform);
    }



} 