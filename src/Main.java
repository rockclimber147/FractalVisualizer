
import display.GameWindow;
import model.GridCell;
import model.Renderer;

public class Main {
    public static void main(String[] args) {
        int edgeCellCount = 500;
        GridCell.setIterationCount(160);

        Renderer renderer = new Renderer(edgeCellCount);
        GameWindow window = new GameWindow();
        window.add(renderer.getDisplay());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}