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
import java.util.Random;

public class Person implements DrawingObject{
    Random random = new Random();

    String state; // This can be "walking", "idling", or "crawling" (maybe make this an enum)
    int animationFrameNum;
    Color shirtColor;
    Color skinColor;
    Color pantsColor;

    // The variables with 1 is the initial state then x2 is the final destination for walking states
    int x1, x2;
    int y1, y2;
    double scale1, scale2;

    // These are the actual current values of the person
    int x,y;
    double scale;

    // Constructor to create walking people
    public Person(String state, Color shirtColor, Color pantsColor, Color skinColor, int x1, int y1, int x2, int y2, double scale1, double scale2){
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
    }

    // Constructor to create idling people
    public Person(String state, Color shirtColor, Color pantsColor,  Color skinColor, int x1, int y1, double scale1){
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
    }

    private void makeHead(Graphics2D g2d, double headX, double headY, double width){
        AffineTransform transform = new AffineTransform();
        transform.setToIdentity();
        g2d.setTransform(transform);
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
        int frameNum = animationFrameNum % 10 + 1;
        if (frameNum == 1){
            makeHead(g2d, 0.0, 0.0, 73.7); // Head
            makeLimb(g2d, 37, 97.2, -39, 20.2, 88.8, skinColor); // Right Arm
            makeLimb(g2d, 27.3, 182.9, 31.8, 21.2, 117.7, pantsColor); // Left Leg
            makeLimb(g2d, 34, 194, -30.4, 21.2, 117.7, pantsColor); // Right Leg
            makeShirt(g2d, 0, 84.1, 74.1, 116.9); // Shirt
            makeLimb(g2d, 19.3, 80.9, 41.8, 20.6, 92.2, skinColor); // Left Arm
        } else if (frameNum == 2 || frameNum == 10){
            makeHead(g2d, 4.3, 0.0, 73.7); // Head
            makeLimb(g2d, 32.1, 101.3, -33.2, 20.2, 88.8, skinColor); // Right Arm
            makeLimb(g2d, 43.6, 187.8, 35.7, 21.2, 117.7, pantsColor); // Left Leg
            makeShirt(g2d, 0.6, 79.7, 74.1, 116.9); // Shirt
            makeLimb(g2d, 32.4, 186.6, -10.3, 21.2, 117.7, pantsColor); // Right Leg
            makeLimb(g2d, 9.1, 82.7, 14.6, 20.6, 92.2, skinColor); // Left Arm
        } else if (frameNum == 3 || frameNum == 9){
            makeHead(g2d, 2.4, 0.0, 73.7); // Head
            makeLimb(g2d, 43.9, 85, -15, 20.2, 88.8, skinColor); // Right Arm
            makeLimb(g2d, 48.8, 181, 26.6, 21.2, 117.7, pantsColor); // Right Leg
            makeShirt(g2d, 0.2, 80.7, 74.1, 116.9); // Shirt
            makeLimb(g2d, 18.4, 194.8, -8, 21.2, 117.7, pantsColor); // Left Leg
            makeLimb(g2d, 0.2, 89.6, -11.9, 20.6, 92.2, skinColor); // Left Arm
        } else if (frameNum == 4 || frameNum == 8){
            makeHead(g2d, 5.8, 0.0, 74.1); // Head
            makeLimb(g2d, 6.7, 186.9, 11.1, 21.2, 117.7, pantsColor); // Left Leg
            makeShirt(g2d, 0.2, 80.5, 74.1, 116.9); // Shirt
            makeLimb(g2d, 29.6, 189.2, -5.9, 21.2, 117.7, pantsColor); // Right Leg
            makeLimb(g2d, -4, 89.3, -9.7, 20.6, 92.2, skinColor); // Left Arm
        } else if (frameNum == 5 || frameNum == 7){
            makeHead(g2d, 11.1, 0.0, 74.1); // Head
            makeLimb(g2d, 18.3, 191.8, 23.8, 21.2, 117.7, pantsColor); // Left Leg
            makeShirt(g2d, 0.7, 80.5, 74.1, 116.9); // Shirt
            makeLimb(g2d, 37.7, 196.7, -15.2, 21.2, 116.1, pantsColor); // Right Leg
            makeLimb(g2d, -7.9, 91.7, -32.6, 20.6, 92.2, skinColor); // Left Arm
        } else if (frameNum == 6) {
            makeHead(g2d, 6.8, 0.0, 73.7); // Head
            makeLimb(g2d, 41.9, 90.6, 38.8, 20.2, 88.8, skinColor); // Right Arm
            makeLimb(g2d, 32.3, 192.3, -15.2, 21.2, 117.7, pantsColor); // Right Leg
            makeShirt(g2d, 0.4, 79.5, 74.1, 116.9); // Shirt
            makeLimb(g2d, 21.1, 175.3, 39.5, 21.2, 117.7, pantsColor); // Left Leg
            makeLimb(g2d, 3, 99.9, -46.1, 20.6, 92.2, skinColor); // Left Arm
        }
    }

    public void incrementAnimation(){
        animationFrameNum++;
    }

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform originalTransform = g2d.getTransform();

        // If we had a walking person 
        if (state.equals("walking")){
            // See if the person is already past the set bounds. If yes hen make it idle
            if ((x2-x)*(x2-x1) <= 0 && (y2-y)*(y2-y1) <= 0) {
                state = "idling";
                getIdleFrame(g2d);
            } else {
                // Every fourth walking frame (the frame closest looking to idle) there is a 10% chance that the character will idle for a few frames
                if ((animationFrameNum % 10 + 1 == 1) && random.nextInt(9) == 0){
                    // Idle for 1 cycle
                    getIdleFrame(g2d);
                    state = "idling";
                } else { 
                    getWalkingFrame(g2d);
                }
            }

        } else if (state.equals("idling")){
            
            getIdleFrame(g2d);

            // if idling but has somewhere to go
            if ((x2-x)*(x2-x1) >= 0 && (y2-y)*(y2-y1) >= 0) {
                if (animationFrameNum % 10 + 1 == 10 || animationFrameNum % 10 + 1 == 1){
                    state = "walking";
                }
            }
        }

        incrementAnimation();
        if (state.equals("walking")){
            // change the current x and y position
            if ((x2-x)*(x2-x1) > 0){
                x += 20 * (x2-x1)/Math.abs((x2-x1));
            }
            if ((y2-y)*(y2-y1) > 0){
                y += 20 * (y2-y1)/Math.abs((y2-y1));
            }

            if ((scale2-scale)*(scale2-scale1) > 0){
                scale += 0.005;
            }
        }

        g2d.setTransform(originalTransform);
    }



} 