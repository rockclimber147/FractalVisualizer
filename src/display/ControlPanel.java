package display;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Renderer;

import java.awt.*;

public class ControlPanel extends JPanel implements ChangeListener {
    private final Renderer renderer;
    private final LabelSlider iteration;
    private final LabelSlider hueOffset;
    private final LabelSlider hueFactor;
    public ControlPanel(Renderer renderer, int edgeCellCount){
        this.renderer = renderer;
        int labelSliderHeight = edgeCellCount / 3 - 50;
        this.iteration = new LabelSlider("Iteration Count: 50", 1, 1000, 50, new Dimension(edgeCellCount, labelSliderHeight));
        this.hueOffset = new LabelSlider("Hue Offset: 500", 0, 1000, 500, new Dimension(edgeCellCount, labelSliderHeight));
        this.hueFactor = new LabelSlider("Hue Cycle Factor: 200", 2, 400, 200, new Dimension(edgeCellCount, labelSliderHeight));

        this.add(iteration.getLabel());
        this.add(iteration.getSlider());
        iteration.getSlider().addChangeListener(this);

        this.add(hueOffset.getLabel());
        this.add(hueOffset.getSlider());
        hueOffset.getSlider().addChangeListener(this);

        this.add(hueFactor.getLabel());
        this.add(hueFactor.getSlider());
        hueFactor.getSlider().addChangeListener(this);

        this.setBounds(edgeCellCount, 0 , edgeCellCount, edgeCellCount);

        this.setFocusable(true);
        this.setOpaque(true);
        this.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider iterationSlider = iteration.getSlider();
        JSlider hueOffsetSlider = hueOffset.getSlider();
        JSlider hueFactorSlider = hueFactor.getSlider();
        System.out.println("SLIDER HAS BEEN SLID!!!!");
        if (e.getSource().equals(iterationSlider)){
            System.out.println(1);
            int value = iterationSlider.getValue();
            iteration.getLabel().setText("Iteration Count: " + value);
            renderer.updateGlobalIterationCount(value);
            renderer.updateGrid();
        } else if (e.getSource().equals(hueOffsetSlider)){
            int value = hueOffsetSlider.getValue();
            System.out.println(2);
            hueOffset.getLabel().setText("Hue Offset: " + value);
            renderer.updateGlobalColorOffset((double) value / 1000);
            renderer.updateGridColors();
        } else if (e.getSource().equals(hueFactorSlider)){
            int value = hueFactorSlider.getValue();
            System.out.println(3);
            hueFactor.getLabel().setText("Hue Cycle Factor: " + value);
            renderer.updateGlobalColorFactor(value);
            renderer.updateGridColors();
        }
    }
}
