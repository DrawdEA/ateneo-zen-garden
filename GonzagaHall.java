import java.awt.*;
import java.util.ArrayList;

public class GonzagaHall implements DrawingObject {
    int x, y;
    Rectangle buildingBase;
    int opacity = 255;
    
    public GonzagaHall(int xPosition, int yPosition){
        x = xPosition;
        y = yPosition;

        buildingBase = new Rectangle(x, x+485, x+485, x, y, y+30, y+220, y+250, new Color(201,115,100,opacity));
    }

    private ArrayList<Rectangle> window(int x, int y, Color color){
        ArrayList<Rectangle> window = new ArrayList();
        
        // Add Glass
        window.add(new Rectangle(x, x+55, x+55, x, y, y, y+33, y+33, color));
        
        // Add Frame
        // Vertical Frames
        for (int i = 0; i < 4; i++) {
            window.add(new Rectangle(x + (i*55/3), x + 1 + (i*55/3), x + 2 + (i*55/3), x + (i*55/3), y, y, y+33, y+33, Color.WHITE));
        }
        // Horizontal Frames
        for (int i = 0; i < 3; i++) {
            window.add(new Rectangle(x, x+55, x+55, x, y + (i*33/2), y + 2 + (i*33/2),  y + 1 + (i*33/2), y + (i*33/2), Color.WHITE));
        }

        return window;
    }

    private ArrayList<Rectangle> outerAccent(int x, int y, Color color){
        ArrayList<Rectangle> outerAccent = new ArrayList();
        
        // White "hat" of Gonz
        outerAccent.add(new Rectangle(x, x+485, x+485, x, y, y+30, y+70, y+35, color));

        // Vertical accents
        for (int i = 0; i < 7; i++) {
            outerAccent.add(new Rectangle(x+57+(i*(12+57)), x+57+(i*(12+57))+13, x+57+(i*(12+57))+13, x+57+(i*(12+57)), y+10+(i*25/6), y+10+(i*25/6), y+180, y+180, color));
        }

        // Horizontal Accent
        outerAccent.add(new Rectangle(x,x+485, x+485, x, y+160+10, y+160+15, y+160+15+10, y+160+25, color));

        return outerAccent;
    }

    @Override
    public void draw(Graphics2D g2d) {
        
        // Building wall everything is on
        buildingBase.draw(g2d);

        // First row of windows
        for (int i = 0; i < 7; i++) {
            for (Rectangle windowObject : window(x+1 + (i*(12+57)),y+36 + (i*5), new Color(149,143,144,opacity))) {
                windowObject.draw(g2d);
            }
        }

        // Second row of windows
        for (int i = 0; i < 7; i++) {
            for (Rectangle windowObject : window(x+1 + (i*(12+57)),y+100 + (i*5), new Color(149,143,144,opacity))) {
                windowObject.draw(g2d);
            }
        }

        // White accents
        for (Rectangle accent : outerAccent(x, y, new Color(198,187,195,opacity))){
            accent.draw(g2d);
        }

    }
}