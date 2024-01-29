package input;

import model.Renderer;

import java.awt.*;

public class MouseInputState {
    private final Renderer renderer;
    private boolean leftMouseCurrentlyPressed;

    private final Point previousMouseLocation;
    private final Point currentMouseLocation;

    private int mouseDeltaX;
    private int mouseDeltaY;

    public MouseInputState(Renderer renderer){
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
    public void setLeftMouseCurrentlyPressed(boolean leftMouseCurrentlyPressed) {
        this.leftMouseCurrentlyPressed = leftMouseCurrentlyPressed;
    }

    public int getMouseDeltaX() {
        return mouseDeltaX;
    }

    public int getMouseDeltaY() {
        return mouseDeltaY;
    }

    public void update(int newX, int newY, int zoomAmount){
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
