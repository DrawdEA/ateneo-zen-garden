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
import java.util.*;

public class Bush implements DrawingObject {
    int x,y;
    int w,h;
    int numOfBushClusters;
    ArrayList<ArrayList<Integer>> bushClusters;

    int opacity = 255;
    Color base = new Color(8,88,4,opacity);
    Color midtone = new Color(63,160,60, opacity); 
    Color highlight = new Color(81,215,53,opacity);

    public Bush(int xPosition, int yPosition, int width, int height) {
        x = xPosition;
        y = yPosition;
        w = width;
        h = height;
        bushClusters = new ArrayList<ArrayList<Integer>>();

        Random random = new Random();
        numOfBushClusters = random.nextInt((int)h*w/350, (int)h*w/250);

        for (int i = 0; i < numOfBushClusters; i++) {
            int geometricMean =  (int) Math.sqrt(h*w);
            int cWidth = random.nextInt(geometricMean/15, geometricMean/5);
            int cHeight = random.nextInt(geometricMean/15, geometricMean/5);
            int cX = random.nextInt(x, x + w - cWidth);
            int cY = random.nextInt(y, y + h - cHeight);
            
            bushClusters.add(new ArrayList<Integer>());
            bushClusters.get(i).add(cX);
            bushClusters.get(i).add(cY);
            bushClusters.get(i).add(cWidth);
            bushClusters.get(i).add(cHeight);
        }
        
    }

    public Rectangle boundingBox(){
        return new Rectangle(
            x,
            y,
            x+w,
            y,
            x+w,
            y+h,
            x,
            y+h,
        base);
    }

    public ArrayList<Ellipse> bushClusters(){
        ArrayList<Ellipse> bushClusterDrawings = new ArrayList<>();

        for (int i = 0; i < numOfBushClusters; i++) {   
            int xPos = bushClusters.get(i).get(0);
            int yPos = bushClusters.get(i).get(1);
            int width = bushClusters.get(i).get(2);
            int height = bushClusters.get(i).get(3);

            bushClusterDrawings.add(new Ellipse(
                xPos,
                yPos,
                width,
                height,
                base
            ));

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