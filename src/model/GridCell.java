package model;

import complexNumbers.ComplexNumber;

import java.awt.*;

public class GridCell {
    private static double colorScale = 100;
    private static double colorOffset = 0.5;

    private static int iterationCount = 40;
    private final ComplexNumber coordinates;
    private final ComplexNumber value;
    private int lastIterationCount;
    private Color cellColor;

    public GridCell(ComplexNumber coordinates){
        this.coordinates = coordinates;
        this.value = coordinates.copy();
        this.cellColor = new Color(0,0,0);
    }

    public static void setIterationCount(int newCount){
        iterationCount = newCount;
    }

    public void iterate(){
        this.lastIterationCount = -1;
        for (int i = 0; i < iterationCount; i++){
            this.value.power(2);
            this.value.add(this.coordinates);

            // If the value escapes the local box, note the amount of iterations it took
            if (this.value.getDistanceFromOrigin() > 2){
                this.lastIterationCount = i;
                updateColor();
                return;
            }
        }
        updateColor();
    }
    private void updateColor(){
        if (this.lastIterationCount < 0){
            this.cellColor = new Color(0,0,0);
        } else {
            this.cellColor = new Color(Color.HSBtoRGB((float) (lastIterationCount/colorScale + colorOffset),1,1));
        }

    }

    public void setCoordinates(Double real, double complex){
        coordinates.setRealPart(real);
        coordinates.setComplexPart(complex);
        value.setRealPart(real);
        value.setComplexPart(complex);
    }

    public double[] getDoubleCoords(){
        return new double[] {coordinates.getRealPart(), coordinates.getComplexPart()};
    }

    public Color getCellColor(){
        return cellColor;
    }
}
