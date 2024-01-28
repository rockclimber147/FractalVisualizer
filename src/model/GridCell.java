package model;

import complexNumbers.ComplexNumber;

import java.awt.*;

import static java.lang.Math.max;

public class GridCell {
    private final ComplexNumber coordinates;
    private final ComplexNumber value;
    private Color cellColor;

    public GridCell(ComplexNumber coordinates){
        this.coordinates = coordinates;
        this.value = coordinates.copy();
        this.cellColor = new Color(0,0,0);
    }

    public void iterate(int count){
        for (int i = 0; i < count; i++){
            this.value.power(2);
            this.value.add(this.coordinates);
            if (Math.abs(this.value.getComplexPart()) > 2 || Math.abs(this.value.getRealPart()) > 2){
                setColor(i);
                return;
            }
        }
        setColor(-1);
    }
    private void setColor(double colorFactor){
        if (colorFactor < 0){
            this.cellColor = new Color(0,0,0);
        } else {
            this.cellColor = new Color(Color.HSBtoRGB((float) colorFactor/100,1,1));
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
