package display;

import input.InputHandler;
import model.Grid;
import model.GridCell;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Displays the rendered Mandelbrot set
 * @author Daylen Smith
 * @version 2024
 */
public class DisplayPanel extends JPanel{
    // Screen Settings
    private final int  pixelEdgeCount;
    private final Grid grid;

    private BufferedImage fractalImage;

    /**
     * Creates a display panel for the rendered Mandelbrot set
     * @param grid The grid of grid cells
     * @param pixelEdgeCount The edge length of the display in pixels
     * @param inputHandler The mouse input handler
     */
    public DisplayPanel(final Grid grid, final int pixelEdgeCount, final InputHandler inputHandler) {
        this.pixelEdgeCount = pixelEdgeCount;
        this.grid = grid;
        this.addMouseListener(inputHandler);
        this.addMouseMotionListener(inputHandler);
        this.addMouseWheelListener(inputHandler);
        this.setPreferredSize(new Dimension(pixelEdgeCount, pixelEdgeCount));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void buildFractalImage() {
        int size = pixelEdgeCount;
        fractalImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        GridCell[][] cells = this.grid.getCells();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fractalImage.setRGB(i, j, cells[i][j].getCellColor().getRGB());
            }
        }
    }

    /**
     * Paints the display panel based on the color states of the grid cells
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(final Graphics g){
        super.paintComponent(g);
        if (fractalImage != null) {
            g.drawImage(fractalImage, 0, 0, null);
        }
    }

    public void update() {
        this.buildFractalImage();
        this.repaint();
    }

}
