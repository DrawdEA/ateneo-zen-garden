/**
 * The Bush class generates a bush shape given a specific width and height.
 * The colors of the bush is a gradient of 3 specific shades of green.
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
import java.util.*;

public class Bush implements DrawingObject {
    int x;
    int y;
    int w;
    int h;
    int numOfBushClusters;
    ArrayList<ArrayList<Integer>> bushClusters;

    int opacity = 255;
    Color base = new Color(15, 58, 42);
    Color midtone = new Color(13, 48, 44); 
    Color highlight = new Color(23, 112, 73);

    /**
     * Instantiate a bush object.
     * 
     * @param xPosition x anchor position of the bush
     * @param yPosition y anchor position of the bush
     * @param width width of the bush
     * @param height height of the bush
     */
    public Bush(int xPosition, int yPosition, int width, int height) {
        x = xPosition;
        y = yPosition;
        w = width;
        h = height;
        bushClusters = new ArrayList<ArrayList<Integer>>();

        Random random = new Random();
        numOfBushClusters = random.nextInt((int) h * w / 200, (int) h * w / 50);

        for (int i = 0; i < numOfBushClusters; i++) {
            int geometricMean =  (int) Math.sqrt(h * w);
            int cWidth = random.nextInt(geometricMean / 15, geometricMean / 7);
            int cHeight = random.nextInt(geometricMean / 15, geometricMean / 7);
            int cX = random.nextInt(x, x + w - cWidth);
            int cY = random.nextInt(y, y + h - cHeight);
            
            bushClusters.add(new ArrayList<Integer>());
            bushClusters.get(i).add(cX);
            bushClusters.get(i).add(cY);
            bushClusters.get(i).add(cWidth);
            bushClusters.get(i).add(cHeight);
        }
    }

    /**
     * Creates a rectangle to put the bush in.
     * 
     * @return the rectangle
     */
    public Rectangle boundingBox(){
        return new Rectangle(x, y, x+w, y, x+w, y+h, x, y+h, base);
    }

    /**
     * Initializes the bush drawings.
     * 
     * @return an array list of the individual bush ellipses
     */
    public ArrayList<Ellipse> bushClusters() {
        ArrayList<Ellipse> bushClusterDrawings = new ArrayList<>();

        for (int i = 0; i < numOfBushClusters; i++) {   
            int xPos = bushClusters.get(i).get(0);
            int yPos = bushClusters.get(i).get(1);
            int width = bushClusters.get(i).get(2);
            int height = bushClusters.get(i).get(3);

            bushClusterDrawings.add(new Ellipse(xPos, yPos, width, height, base));

            bushClusterDrawings.add(new Ellipse(
                xPos + (int) (width * 0.75 / 2),
                yPos - (int) (height * 0.75),
                (int)(width * 0.75),
                (int)(height * 0.75),
                midtone
            ));

            bushClusterDrawings.add(new Ellipse(
                xPos + (int) (width * 0.75 * 0.75 / 2),
                yPos - (int) (height * 1.25),
                (int)(width * 0.75 * 0.75),
                (int)(height * 0.75 * 0.75),
                highlight
            ));
        }
        return bushClusterDrawings;
    }

    /**
     * Draws the bushes instantiated by the bush clusters.
     */
    @Override
    public void draw(Graphics2D g2d) {
        // Bush Base
        // boundingBox().draw(g2d);

        // Bush Clusters
        for (Ellipse bushCluster : bushClusters()){
            bushCluster.draw(g2d);
        }
    }
}