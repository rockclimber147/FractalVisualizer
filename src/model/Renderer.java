package model;

import display.GameDisplayPanel;
import display.GameWindow;
import input.InputHandler;
import input.MouseInputState;

public class Renderer {
    private static final double DEFAULT_X = -2;
    private static final double DEFAULT_Y = 2;
    private static final double DEFAULT_WIDTH = 4;
    private final MouseInputState inputState;
    private final Grid grid;

    private final GameDisplayPanel display;
    private final double zoomFactor;
    private final int edgeCellCount;

    private double zoomLevel;
    private int iterationCount;
    public Renderer(int edgeCellCount, int iterationCount){
        this.edgeCellCount = edgeCellCount;
        this.inputState = new MouseInputState(this);
        this.grid = new Grid(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, this.edgeCellCount);
        this.display = new GameDisplayPanel(this.grid, edgeCellCount, new InputHandler(inputState));
        this.zoomFactor = 1.5;
        this.zoomLevel = 1 / (DEFAULT_WIDTH / edgeCellCount);
        this.iterationCount = iterationCount;
        this.grid.update(iterationCount);
    }

    public GameDisplayPanel getDisplay(){
        return display;
    }

    public void updateFromInputState(){
        if (inputState.getMouseDeltaX() == 0 && inputState.getMouseDeltaY() == 0 || !inputState.isLeftMouseCurrentlyPressed()){
            return;
        }
        grid.translate(-inputState.getMouseDeltaX() / zoomLevel, inputState.getMouseDeltaY() / zoomLevel);
        grid.update(this.iterationCount);
        display.repaint();
        System.out.println(grid);
    }

    public void zoomOnPoint(int targetX, int targetY, double zoomAmount){
        if (zoomAmount == 1){
            // zoom out
            this.zoomLevel /= zoomFactor;
        } else {
            // zoom in
            this.zoomLevel *= zoomFactor;
        }
        double[] newXvals = this.scale(targetX, 0, zoomAmount);
        double[] newYvals = this.scale(targetY, 1, zoomAmount);
        this.scale(targetY, 1, zoomAmount);
        grid.resize(newXvals[1], newYvals[1], newXvals[0], newYvals[0]);
        grid.update(iterationCount);
        display.repaint();
    }

    private double[] scale(int targetCoordinate, int axisIndex, double zoomAmount){
        double fractionAbove = (double) targetCoordinate / edgeCellCount;
        return new double[]{
                grid.getTopLeft()[axisIndex] - zoomAmount * fractionAbove * zoomFactor *
                        (grid.getBottomRight()[axisIndex] - grid.getTopLeft()[axisIndex]),
                grid.getBottomRight()[axisIndex] + zoomAmount * (1 - fractionAbove) * zoomFactor *
                        (grid.getBottomRight()[axisIndex] - grid.getTopLeft()[axisIndex])
        };

    }
}
