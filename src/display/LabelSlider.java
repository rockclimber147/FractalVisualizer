package display;

import javax.swing.*;
import java.awt.*;

/**
 * Packs a JSlider and corresponding label
 * @author Daylen Smith
 * @version 2024
 */
public class LabelSlider {
    private static final int LABEL_HEIGHT = 15;
    private final JLabel label;
    private final JSlider slider;

    /**
     * Creates a new LabelSlider
     * @param label The text for the label
     * @param min The minimum slider value
     * @param max The maximum slider value
     * @param value The initial slider value
     * @param dimension The dimensions of the Slider
     */
    public LabelSlider(final String label, final int min, final int max, final int value, final Dimension dimension){
        this.label = new JLabel(label);
        this.label.setPreferredSize(new Dimension(dimension.width, LABEL_HEIGHT));
        this.slider = new JSlider(min, max, value);
        this.slider.setPreferredSize(new Dimension(dimension.width, dimension.height - LABEL_HEIGHT));
    }

    /**
     * Returns the JLabel
     * @return the JLabel
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * Returns the JSlider
     * @return the JSlider
     */
    public JSlider getSlider() {
        return slider;
    }
}
