package display;

import javax.swing.*;
import javax.swing.JCheckBox;
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
    private static final int LABEL_SLIDER_COUNT = 6;
    private static final int EDGE_LABEL_HEIGHT = 20;
    private static final int DEFAULT_ITERATION_COUNT = 100;
    private static final int DEFAULT_HUE_OFFSET = 500;
    private static final int DEFAULT_HUE_CYCLE_FACTOR = 200;
    private static final int DEFAULT_EXPONENT = 2;
    private final Renderer renderer;
    private final LabelSlider iteration;
    private final LabelSlider hueOffset;
    private final LabelSlider hueFactor;
    private final LabelSlider exponent;
    private final JCheckBox juliaToggle;
    private final LabelSlider juliaRealSlider;
    private final LabelSlider juliaImagSlider;
    private final JLabel displayPanelWidth;

    /**
     * Creates a ControlPanel object
     * @param renderer The renderer to control
     * @param edgeCellCount The side length of the panel in pixels
     */
    public ControlPanel(final Renderer renderer, final int edgeCellCount){
        this.renderer = renderer;
        int labelSliderHeight = edgeCellCount / LABEL_SLIDER_COUNT - EDGE_LABEL_HEIGHT;

        this.iteration = new LabelSlider("Iteration Count: " + DEFAULT_ITERATION_COUNT,
                1, 2500, 100, new Dimension(edgeCellCount, labelSliderHeight));
        this.hueOffset = new LabelSlider("Hue Offset: " + DEFAULT_HUE_OFFSET,
                0, 1000, 500, new Dimension(edgeCellCount, labelSliderHeight));
        this.hueFactor = new LabelSlider("Hue Cycle Factor: " + DEFAULT_HUE_CYCLE_FACTOR,
                2, 400, 200, new Dimension(edgeCellCount, labelSliderHeight));
        this.exponent = new LabelSlider("Equation: z^" + DEFAULT_EXPONENT + " + c",
                1, 10, 2, new Dimension(edgeCellCount, labelSliderHeight));

        this.displayPanelWidth = new JLabel("View square is : " + String.format("%1.3e", 4.00) + " units wide");
        this.juliaToggle = new JCheckBox("Toggle Julia Set");
        this.juliaRealSlider = new LabelSlider("Julia C Real: 0.0", -2000, 2000, 0, new Dimension(edgeCellCount, labelSliderHeight));
        this.juliaImagSlider = new LabelSlider("Julia C Imag: 0.0", -2000, 2000, 0, new Dimension(edgeCellCount, labelSliderHeight));

        this.incorporateLabelSlider(exponent);
        this.incorporateLabelSlider(iteration);
        this.incorporateLabelSlider(hueOffset);
        this.incorporateLabelSlider(hueFactor);
        this.add(this.juliaToggle);
        this.incorporateLabelSlider(juliaRealSlider);
        this.incorporateLabelSlider(juliaImagSlider);
        this.add(displayPanelWidth);

        this.juliaToggle.addActionListener(e -> {
            boolean isJulia = juliaToggle.isSelected();
            renderer.setJuliaMode(isJulia);
            renderer.updateGrid();

        });

        this.setBounds(edgeCellCount, 0 , edgeCellCount, edgeCellCount);
        this.setFocusable(true);
        this.setOpaque(true);
        this.setVisible(true);
    }

    public void updateZoomDisplay(double currentViewWidth){
        displayPanelWidth.setText("View square is : " + String.format("%1.3e", currentViewWidth) + " units wide");
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
        }else if (e.getSource().equals(juliaRealSlider.getSlider())) {
            double value = juliaRealSlider.getSlider().getValue() / 1000.0;
            juliaRealSlider.getLabel().setText("Julia C Real: " + value);
            renderer.updateJuliaConstantReal(value);
            renderer.updateGrid();
        } else if (e.getSource().equals(juliaImagSlider.getSlider())) {
            double value = juliaImagSlider.getSlider().getValue() / 1000.0;
            juliaImagSlider.getLabel().setText("Julia C Imag: " + value);
            renderer.updateJuliaConstantImag(value);
            renderer.updateGrid();
        }
    }
}
