package input;

import model.Renderer;

import java.awt.*;

/**
 * Describes the current state of the mouse
 * @author Daylen Smith
 * @version 2024
 */
public class MouseInputState {
    private final Renderer renderer;
    private boolean leftMouseCurrentlyPressed;

    private final Point previousMouseLocation;
    private final Point currentMouseLocation;

    private int mouseDeltaX;
    private int mouseDeltaY;

    /**
     * Creates a new mouse input state
     * @param renderer The renderer to use with the input
     */
    public MouseInputState(final Renderer renderer){
        this.renderer = renderer;
        this.leftMouseCurrentlyPressed = false;
        this.previousMouseLocation = new Point(0,0);
        this.currentMouseLocation = new Point(0,0);
        this.mouseDeltaX = 0;
        this.mouseDeltaY = 0;
    }

    public boolean isLeftMouseCurrentlyPressed() {
        return leftMouseCurrentlyPressed;
    }
    public void setLeftMouseCurrentlyPressed(final boolean leftMouseCurrentlyPressed) {
        this.leftMouseCurrentlyPressed = leftMouseCurrentlyPressed;
    }

    public int getMouseDeltaX() {
        return mouseDeltaX;
    }

    public int getMouseDeltaY() {
        return mouseDeltaY;
    }

    /**
     * Updates the mouse state based off of the previous state
     * @param newX The incoming mouse x position
     * @param newY The incoming mouse y position
     * @param zoomAmount The incoming mouse scroll direction
     */
    public void update(final int newX, final int newY, final int zoomAmount){
        previousMouseLocation.setLocation(currentMouseLocation);
        currentMouseLocation.setLocation(newX, newY);

        mouseDeltaX = currentMouseLocation.x - previousMouseLocation.x;
        mouseDeltaY = currentMouseLocation.y - previousMouseLocation.y;

        renderer.updateFromInputState();
        if (zoomAmount != 0){
            renderer.zoomOnPoint(newX, newY, zoomAmount);
        }
    }
}
