package input;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class InputHandler implements MouseInputListener, MouseWheelListener {
    MouseInputState mouseState;

    public InputHandler(MouseInputState mouseState) {
        this.mouseState = mouseState;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseState.setLeftMouseCurrentlyPressed(true);
        mouseState.update(e.getX(), e.getY(), 0);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseState.setLeftMouseCurrentlyPressed(false);
        mouseState.update(e.getX(), e.getY(), 0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseState.update(e.getX(), e.getY(), 0);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseState.update(e.getX(), e.getY(), 0);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // positive = scroll down
        mouseState.update(e.getX(), e.getY(), e.getWheelRotation());
        System.out.println("Scrolled: " + e.getWheelRotation());
    }
}
