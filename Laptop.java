import java.awt.*;

public class Laptop implements DrawingObject {
    int xPosition;
    int yPosition;
    Rectangle border;
    Rectangle screen;
    Rectangle keyboard;
    boolean isOpen;
    
    public Laptop(int x, int y, boolean iO) {
        isOpen = iO;
        xPosition = x; // TODO: actually implement the x and y coordinates so that the positions are not hardcoded
        yPosition = y;
        border = new Rectangle(300, 500, 500, 300, 460, 460, 585, 585, Color.BLACK);
        screen = new Rectangle(305, 495, 495, 305, 465, 465, 571, 571, Color.WHITE);
        keyboard = new Rectangle(298, 502, 510, 290, 585, 585, 600, 600, isOpen ? Color.GRAY : Color.BLACK);
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (isOpen) {
            border.draw(g2d);
            screen.draw(g2d);
        }
        
        keyboard.draw(g2d);
    }
}
