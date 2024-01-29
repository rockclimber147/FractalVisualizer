package model;

import display.ControlPanel;
import display.GameDisplayPanel;
import input.InputHandler;
import input.MouseInputState;

public class Renderer {
    private static final double DEFAULT_X = -2;
    private static final double DEFAULT_Y = 2;
    private static final double DEFAULT_WIDTH = 4;
    private static final double DEFAULT_ZOOM_FACTOR = 1.1;
    private final MouseInputState inputState;
    private final Grid grid;

    private final GameDisplayPanel display;
    private final ControlPanel controlPanel;
    private final double zoomFactor;
    private final int edgeCellCount;

    public Renderer(int edgeCellCount){
        this.edgeCellCount = edgeCellCount;
        this.inputState = new MouseInputState(this);
        this.grid = new Grid(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, this.edgeCellCount);
        this.display = new GameDisplayPanel(this.grid, edgeCellCount, new InputHandler(inputState));
        this.controlPanel = new ControlPanel(this, edgeCellCount - 5);
        this.zoomFactor = DEFAULT_ZOOM_FACTOR;
        this.grid.update();
    }

    public void setGlobalIterationCount(int newCount){
        GridCell.setIterationCount(newCount);
        grid.update();
        display.repaint();
    }

    public void setGlobalColorOffset(double newOffset){
        GridCell.setColorOffset(newOffset);
        display.repaint();
    }

    public void setGlobalColorFactor(double newFactor){
        GridCell.setColorScale(newFactor);
        display.repaint();
    }

    public GameDisplayPanel getDisplay(){
        return display;
    }
    public ControlPanel getControlPanel(){
        return controlPanel;
    }

    public void updateFromInputState(){
        if (inputState.getMouseDeltaX() == 0 && inputState.getMouseDeltaY() == 0 || !inputState.isLeftMouseCurrentlyPressed()){
            return;
        }
        grid.translate((double) -inputState.getMouseDeltaX() / edgeCellCount, (double) inputState.getMouseDeltaY() / edgeCellCount);
        grid.update();
        display.repaint();
        System.out.println(grid);
    }

    public void updateGridColors(){
        this.grid.updateColors();
    }

    public void updateGrid(){
        this.grid.update();
    }

    public void zoomOnPoint(int targetX, int targetY, double zoomAmount){
        double newEdgeLength;
        double previousEdgeLength = this.grid.getEdgeLength();
        if (zoomAmount == 1){
            // zoom out
            newEdgeLength = previousEdgeLength * zoomFactor;
        } else {
            // zoom in
            newEdgeLength = previousEdgeLength / zoomFactor;
        }

        double previousTopLeftX = grid.getTopLeft()[0];
        double previousTopLeftY = grid.getTopLeft()[1];

        double screenEventRelativeX = ((double) targetX / edgeCellCount);
        double screenEventRelativeY = ((double) targetY / edgeCellCount);

        double modelEventX = previousTopLeftX + previousEdgeLength * screenEventRelativeX;
        double modelEventY = previousTopLeftY - previousEdgeLength * screenEventRelativeY;

        double newTopLeftX = modelEventX - newEdgeLength * screenEventRelativeX;
        double newTopLeftY = modelEventY + newEdgeLength * screenEventRelativeY;

        System.out.println("PrevEL: " + previousEdgeLength + " NewEL: " + newEdgeLength + "\n" +
                        "prevTLX: " + previousTopLeftX + " newTLX: " + newTopLeftX + "\n" +
                "prevTLY: " + previousTopLeftY + " newTLY: " + newTopLeftY);

        grid.resize(newTopLeftX, newTopLeftY, newEdgeLength);
        grid.update();
        display.repaint();
    }
}
