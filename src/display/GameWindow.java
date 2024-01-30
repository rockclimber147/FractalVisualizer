package display;

import javax.swing.*;

/**
 * Sets up the display window
 * @author Daylen Smith
 * @version 2024
 */
public class GameWindow extends JFrame {

    /**
     * Creates a new window
     */
    public GameWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("MANDELBROT");
    }
}
