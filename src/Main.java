
import display.GameWindow;
import model.Renderer;

public class Main {
    public static void main(String[] args) {
        int edgeCellCount = 500;
        int iterationCount = 80;

        Renderer renderer = new Renderer(edgeCellCount, iterationCount);
        GameWindow window = new GameWindow();
        window.add(renderer.getDisplay());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}