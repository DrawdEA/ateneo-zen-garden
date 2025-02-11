import java.awt.*;

public class Line implements DrawingObject {
    int xStart;
    int yStart;
    int xEnd;
    int yEnd;
    float thickness;
    Color color;

    /**
     * Instantiates a line drawing.
     * 
     * @param xS starting x position
     * @param yS starting y position
     * @param xE ending x position
     * @param yE ending y position
     * @param t thickness of the line
     * @param c color of the line
     */
    public Line(int xS, int yS, int xE, int yE, float t, Color c) {
        xStart = xS;
        yStart = yS;
        xEnd = xE;
        yEnd = yE;
        t = thickness;
        color = c;
    }
    
    /**
     * Draws the line.
     * 
     * @param g2d the Graphics2D of the component to place the drawing on
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(color);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawLine(xStart, yStart, xEnd, yEnd);
    }
}
