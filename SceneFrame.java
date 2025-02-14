import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SceneFrame {
    JFrame mainFrame;

    SceneCanvas canvas;
    JButton actionButton;

    /**
     * Instantiates a new frame.
     */
    public SceneFrame() {
        mainFrame = new JFrame();

        canvas = new SceneCanvas();
        actionButton = new JButton("Toggle Laptop");
    }
    
    /**
     * Sets up the GUI.
     */
    public void setUpGUI() {
        // Set up the GUI hierarchy.
        mainFrame.add(canvas, BorderLayout.CENTER);
        mainFrame.add(actionButton, BorderLayout.SOUTH);

        // Set up the miscellaneous details.
        mainFrame.setTitle("Midterm Project - Diesta - Uy, C.");
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        // Pack the frame so that the frame will size itself based on its contents.
        mainFrame.pack();
    }

    /**
     * Sets up the button listeners for the actions.
     */
    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object o = ae.getSource();

                if (o == actionButton) {
                    canvas.toggleLaptop();
                }
            }
        };

        actionButton.addActionListener(buttonListener);
    }
}
