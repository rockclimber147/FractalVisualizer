package display;

import javax.swing.*;
import java.awt.*;

public class LabelSlider {
    JLabel label;
    JSlider slider;

    public LabelSlider(String label, int min, int max, int value, Dimension dimension){
        this.label = new JLabel(label);
        this.label.setPreferredSize(new Dimension(dimension.width, 15));
        this.slider = new JSlider(min, max, value);
        this.slider.setPreferredSize(dimension);
    }

    public JLabel getLabel() {
        return label;
    }

    public JSlider getSlider() {
        return slider;
    }
}
