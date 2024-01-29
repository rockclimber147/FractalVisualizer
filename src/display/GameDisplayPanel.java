package display;

import input.InputHandler;
import model.Grid;
import model.GridCell;

import javax.swing.*;
import java.awt.*;

public class GameDisplayPanel extends JPanel{
    // Screen Settings
    private final int  pixelEdgeCount;
    private final GridCell[][] cells;
    public GameDisplayPanel(Grid grid, int pixelEdgeCount, InputHandler inputHandler) {
        this.pixelEdgeCount = pixelEdgeCount;
        this.cells = grid.getCells();
        this.addMouseListener(inputHandler);
        this.addMouseMotionListener(inputHandler);
        this.addMouseWheelListener(inputHandler);
        this.setPreferredSize(new Dimension(pixelEdgeCount, pixelEdgeCount));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
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
