package model;

import display.ControlPanel;
import display.DisplayPanel;
import input.InputHandler;
import input.MouseInputState;

/**
 * Handles the process of rendering the Mandelbrot set
 * @author Daylen Smith
 * @version 2024
 */
public class Renderer {
    private static final double DEFAULT_X = -2;
    private static final double DEFAULT_Y = 2;
    private static final double DEFAULT_WIDTH = 4;
    private static final double DEFAULT_ZOOM_FACTOR = 1.1;
    private final MouseInputState inputState;
    private final Grid grid;

    private final DisplayPanel display;
    private final ControlPanel controlPanel;
    private final double zoomFactor;
    private final int edgeCellCount;

    /**
     * Creates a new renderer
     * @param edgeCellCount The amount of cells per edge
     */
    public Renderer(final int edgeCellCount){
        this.edgeCellCount = edgeCellCount;
        this.inputState = new MouseInputState(this);
        this.grid = new Grid(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, this.edgeCellCount);
        this.display = new DisplayPanel(this.grid, edgeCellCount, new InputHandler(inputState));
        this.controlPanel = new ControlPanel(this, edgeCellCount - 5);
        this.zoomFactor = DEFAULT_ZOOM_FACTOR;
        this.grid.update();
        this.display.update();
    }

    /**
     * Sets the iteration count and updates the model
     * @param newCount The new iteration count
     */
    public void updateGlobalIterationCount(final int newCount){
        GridCell.setIterationCount(newCount);
        grid.update();
        display.update();
    }

    public void updateGlobalExponent(final int newExponent){
        GridCell.setZExponent(newExponent);
        grid.update();
        display.update();
    }

    /**
     * Sets the color offset and refreshes the display
     * @param newOffset The new color offset
     */
    public void updateGlobalColorOffset(final double newOffset){
        GridCell.setColorOffset(newOffset);
        display.update();
    }

    /**
     * Sets the color change factor and refreshes the display
     * @param newFactor The new color factor
     */
    public void updateGlobalColorFactor(final double newFactor){
        GridCell.setColorScale(newFactor);
        display.update();
    }

    public void setJuliaMode(final boolean mode) {
        GridCell.setIsJulia(mode);
    }

    public void updateJuliaConstantReal(double value) {
        GridCell.setJuliaReal(value);
        grid.update();
        display.update();
    }

    public void updateJuliaConstantImag(double value) {
        GridCell.setJuliaImag(value);
        grid.update();
        display.update();
    }

    public DisplayPanel getDisplay(){
        return display;
    }
    public ControlPanel getControlPanel(){
        return controlPanel;
    }

    /**
     * Reads the current mouse input state and updates the model accordingly
     */
    public void updateFromInputState(){
        if (inputState.getMouseDeltaX() == 0 && inputState.getMouseDeltaY() == 0 || !inputState.isLeftMouseCurrentlyPressed()){
            return;
        }
        grid.translate((double) -inputState.getMouseDeltaX() / edgeCellCount, (double) inputState.getMouseDeltaY() / edgeCellCount);
        grid.update();
        display.update();
    }

    /**
     * Calls on the grid to update its colors
     */
    public void updateGridColors(){
        this.grid.updateColors();
    }

    /**
     * Calls on the grid to iterate and update its colors
     */
    public void updateGrid(){
        this.grid.update();
    }

    /**
     * Resizes the bounding box to emulate zooming in on a point
     * @param targetX The x coordinate of the zoom point in screen pixel space
     * @param targetY The y coordinate of the zoom point in screen pixel space
     * @param zoomFactor An integer denoting zoom in or out
     */
    public void zoomOnPoint(int targetX, int targetY, double zoomFactor){
        double newEdgeLength;
        double previousEdgeLength = this.grid.getEdgeLength();
        if (zoomFactor == 1){
            // zoom out
            newEdgeLength = previousEdgeLength * this.zoomFactor;
        } else {
            // zoom in
            newEdgeLength = previousEdgeLength / this.zoomFactor;
        }

        double previousTopLeftX = grid.getTopLeft()[0];
        double previousTopLeftY = grid.getTopLeft()[1];

        double screenEventRelativeX = ((double) targetX / edgeCellCount);
        double screenEventRelativeY = ((double) targetY / edgeCellCount);

        double modelEventX = previousTopLeftX + previousEdgeLength * screenEventRelativeX;
        double modelEventY = previousTopLeftY - previousEdgeLength * screenEventRelativeY;

        double newTopLeftX = modelEventX - newEdgeLength * screenEventRelativeX;
        double newTopLeftY = modelEventY + newEdgeLength * screenEventRelativeY;

        controlPanel.updateZoomDisplay(newEdgeLength);
        grid.resize(newTopLeftX, newTopLeftY, newEdgeLength);
        grid.update();
        display.update();
    }
}
