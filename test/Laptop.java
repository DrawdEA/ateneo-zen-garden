import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.*;

public class Laptop extends JFrame{

    public Laptop(){
        super("Laptop");
        
        //-----Frame setup-----//
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setSize(800,600);
        this.setLocation(400,0);
        this.setVisible(true);
    }

    public class DrawOpeningLaptop extends JPanel implements ActionListener{
        public Timer timer;
        public int laptopDegrees; 
        public Graphics2D g2d;

        public DrawOpeningLaptop() {
            super.setDoubleBuffered(true);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);

            laptopDegrees = 0;
            timer = new Timer(10, this);
            timer.start();
        }

        @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            g2d = (Graphics2D) g.create();
            g2d.setPaint(Color.green);
                     
            AffineTransform fold = new AffineTransform();

            fold.setToScale(1, Math.cos(Math.toRadians(laptopDegrees)));
            // fold.translate(0, 300 * Math.sin(Math.toRadians(laptopDegrees)));
            
            g2d.setTransform(fold);
            g2d.fillRect(150, 400, 500, 300);  

            g2d.dispose();
        }

        @Override
        public void actionPerformed(ActionEvent ae){
            if (laptopDegrees < 180) {
                laptopDegrees++;
            }
            repaint();
        }
    }

    public void open(){
        this.add(new DrawOpeningLaptop(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Laptop console = new Laptop();
        console.open();
    }
}