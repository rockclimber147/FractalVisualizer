package display;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Renderer;

import java.awt.*;

/**
 * Holds the settings sliders for the Mandelbrot renderer
 * @author Daylen Smith
 * @version 2024
 */
public class ControlPanel extends JPanel implements ChangeListener {
    private static final int LABEL_SLIDER_COUNT = 4;
    private static final int DEFAULT_ITERATION_COUNT = 100;
    private static final int DEFAULT_HUE_OFFSET = 500;
    private static final int DEFAULT_HUE_CYCLE_FACTOR = 200;
    private static final int DEFAULT_EXPONENT = 2;
    private final Renderer renderer;
    private final LabelSlider iteration;
    private final LabelSlider hueOffset;
    private final LabelSlider hueFactor;
    private final LabelSlider exponent;

    /**
     * Creates a ControlPanel object
     * @param renderer The renderer to control
     * @param edgeCellCount The side length of the panel in pixels
     */
    public ControlPanel(final Renderer renderer, final int edgeCellCount){
        this.renderer = renderer;
        int labelSliderHeight = edgeCellCount / LABEL_SLIDER_COUNT;
        this.iteration = new LabelSlider("Iteration Count: " + DEFAULT_ITERATION_COUNT,
                1, 2500, 100, new Dimension(edgeCellCount, labelSliderHeight));
        this.hueOffset = new LabelSlider("Hue Offset: " + DEFAULT_HUE_OFFSET,
                0, 1000, 500, new Dimension(edgeCellCount, labelSliderHeight));
        this.hueFactor = new LabelSlider("Hue Cycle Factor: " + DEFAULT_HUE_CYCLE_FACTOR,
                2, 400, 200, new Dimension(edgeCellCount, labelSliderHeight));
        this.exponent = new LabelSlider("Equation: z^" + DEFAULT_EXPONENT + " + c",
                1, 10, 2, new Dimension(edgeCellCount, labelSliderHeight));

        this.incorporateLabelSlider(iteration);
        this.incorporateLabelSlider(hueOffset);
        this.incorporateLabelSlider(hueFactor);
        this.incorporateLabelSlider(exponent);

        this.setBounds(edgeCellCount, 0 , edgeCellCount, edgeCellCount);
        this.setFocusable(true);
        this.setOpaque(true);
        this.setVisible(true);
    }

    private void incorporateLabelSlider(LabelSlider lSlider){
        this.add(lSlider.getLabel());
        this.add(lSlider.getSlider());
        lSlider.getSlider().addChangeListener(this);
    }

    /**
     * Handles changes in state from any of the sliders
     * @param e a ChangeEvent object
     */
    @Override
    public void stateChanged(final ChangeEvent e) {
        JSlider iterationSlider = iteration.getSlider();
        JSlider hueOffsetSlider = hueOffset.getSlider();
        JSlider hueFactorSlider = hueFactor.getSlider();
        JSlider exponentSlider = exponent.getSlider();

        if (e.getSource().equals(iterationSlider)){
            int value = iterationSlider.getValue();
            iteration.getLabel().setText("Iteration Count: " + value);
            renderer.updateGlobalIterationCount(value);
            renderer.updateGrid();
        } else if (e.getSource().equals(hueOffsetSlider)){
            int value = hueOffsetSlider.getValue();
            hueOffset.getLabel().setText("Hue Offset: " + value);
            renderer.updateGlobalColorOffset((double) value / 1000);
            renderer.updateGridColors();
        } else if (e.getSource().equals(hueFactorSlider)){
            int value = hueFactorSlider.getValue();
            hueFactor.getLabel().setText("Hue Cycle Factor: " + value);
            renderer.updateGlobalColorFactor(value);
            renderer.updateGridColors();
        } else if (e.getSource().equals(exponentSlider)) {
            int value = exponentSlider.getValue();
            exponent.getLabel().setText("Equation: z^" + value + " + c");
            renderer.updateGlobalExponent(value);
            renderer.updateGrid();
        }
    }
}
