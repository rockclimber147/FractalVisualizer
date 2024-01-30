package input;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Handles input from the mouse, including drag, position and scroll wheel
 * @author Daylen Smith
 * @version 2024
 */
public class InputHandler implements MouseInputListener, MouseWheelListener {
    private final MouseInputState mouseState;

    /**
     * Creates an InputHandler object
     * @param mouseState The state of the input
     */
    public InputHandler(final MouseInputState mouseState) {
        this.mouseState = mouseState;
    }
    @Override
    public void mouseClicked(final MouseEvent e) {

    }

    /**
     * Updates the mouse state when mouse is clicked
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(final MouseEvent e) {
        mouseState.setLeftMouseCurrentlyPressed(true);
        mouseState.update(e.getX(), e.getY(), 0);
    }

    /**
     * Updates the mouse state when mouse is released
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
        mouseState.setLeftMouseCurrentlyPressed(false);
        mouseState.update(e.getX(), e.getY(), 0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Updates the mouse state when mouse is dragged
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(final MouseEvent e) {
        mouseState.update(e.getX(), e.getY(), 0);
    }

    /**
     * Updates the mouse state when mouse is moved
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(final MouseEvent e) {
        mouseState.update(e.getX(), e.getY(), 0);
    }

    /**
     * Updates the mouse state when mouse wheel is scrolled
     * @param e the event to be processed
     */
    @Override
    public void mouseWheelMoved(final MouseWheelEvent e) {
        // positive = scroll down
        mouseState.update(e.getX(), e.getY(), e.getWheelRotation());
    }
}
