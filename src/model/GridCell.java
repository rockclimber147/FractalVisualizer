package model;

import complexNumbers.ComplexNumber;

import java.awt.*;

/**
 * Simulates a pixel in the Mandelbrot set
 * @author Daylen Smith
 * @version 2024
 */
public class GridCell {
    private static boolean isJulia = false;
    private static double juliaReal = -.7;
    private static double juliaImag = .27;
    private static double colorScale = 100;
    private static double colorOffset = 0.5;

    private static int iterationCount = 40;

    private static int zExponent = 2;
    private final ComplexNumber coordinates;
    private int lastIterationCount;
    private Color cellColor;

    /**
     * Creates a grid cell
     * @param coordinates The coordinates of the cell as a complex number
     */
    public GridCell(final ComplexNumber coordinates){
        this.coordinates = coordinates;
        this.cellColor = new Color(0,0,0);
    }

    public static void setIsJulia(boolean val) { isJulia = val; }
    public static void setJuliaReal(double val) { juliaReal = val; }
    public static void setJuliaImag(double val) { juliaImag = val; }

    public static void setColorScale(double colorScale) {
        GridCell.colorScale = colorScale;
    }

    public static void setColorOffset(double colorOffset) {
        GridCell.colorOffset = colorOffset;
    }

    public static void setIterationCount(int newCount){
        iterationCount = newCount;
    }

    public static void setZExponent(final int newExponent){ zExponent = newExponent;}

    /**
     * Iterates the value in the cell iteration count times or until the value escapes to infinity
     */
    public void iterate() {
        this.lastIterationCount = -1;

        // Determine initial z and C
        ComplexNumber z = isJulia ? coordinates.copy() : new ComplexNumber(0, 0);
        ComplexNumber c = isJulia ? new ComplexNumber(juliaReal, juliaImag) : coordinates.copy();

        for (int i = 0; i < iterationCount; i++) {
            z.power(zExponent);
            z.add(c);

            if (z.getDistanceFromOriginSquared() > 4) {
                this.lastIterationCount = i;
                updateColor();
                return;
            }
        }
        updateColor();
    }

    /**
     * Updates the color of the cell based on the last iteration count
     */
    public void updateColor(){
        if (this.lastIterationCount < 0){
            this.cellColor = new Color(0,0,0);
        } else {
            this.cellColor = new Color(Color.HSBtoRGB((float) (lastIterationCount/colorScale + colorOffset),1,1));
        }

    }

    /**
     * Sets the coordinates and value complex numbers
     * @param real The real part to set
     * @param complex The complex part to set
     */
    public void setCoordinates(double real, double complex){
        coordinates.setRealPart(real);
        coordinates.setComplexPart(complex);
    }

    public Color getCellColor(){
        return cellColor;
    }
}
