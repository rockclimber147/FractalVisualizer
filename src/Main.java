
import display.GameWindow;
import model.GridCell;
import model.Renderer;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        int edgeCellCount = 400;
        GridCell.setIterationCount(100);

        Renderer renderer = new Renderer(edgeCellCount);
        GameWindow window = new GameWindow();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout());
        mainPanel.add(renderer.getDisplay());
        mainPanel.add(renderer.getControlPanel());
        window.add(mainPanel);
        mainPanel.setPreferredSize(new Dimension(2 * edgeCellCount + 5, edgeCellCount));
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}