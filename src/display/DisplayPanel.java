package display;

import input.InputHandler;
import model.Grid;
import model.GridCell;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the rendered Mandelbrot set
 * @author Daylen Smith
 * @version 2024
 */
public class DisplayPanel extends JPanel{
    // Screen Settings
    private final int  pixelEdgeCount;
    private final Grid grid;

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

    /**
     * Paints the display panel based on the color states of the grid cells
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(final Graphics g){
        super.paintComponent(g);
        GridCell[][] cells = this.grid.getCells();
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1.05f));
        for (int i = 0; i < pixelEdgeCount; i++){
            for (int j = 0; j < pixelEdgeCount; j++){
                g2.setColor(cells[i][j].getCellColor());
                g2.drawLine(i,j,i,j);
            }
        }
        g2.dispose();
    }

}
