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
