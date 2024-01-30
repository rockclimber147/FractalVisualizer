package complexNumbers;

/**
 * Models a complex number with real part and imaginary part
 * @author  Daylen Smith
 * @version 2024
 */
public class ComplexNumber {
    private double realPart;
    private double complexPart;

    /**
     * Creates a complex number
     * @param realPart The real part of the number
     * @param complexPart The complex part of the number
     */
    public ComplexNumber(final double realPart, final double complexPart){
        this.realPart = realPart;
        this.complexPart = complexPart;
    }

    /**
     * Returns the real part of the complex number
     * @return The real part
     */
    public double getRealPart() {
        return realPart;
    }

    /**
     * Sets the real part
     * @param realPart The new real part
     */
    public void setRealPart(final double realPart) {
        this.realPart = realPart;
    }

    /**
     * Returns the complex part of the complex number
     * @return The complex part
     */
    public double getComplexPart() {
        return complexPart;
    }

    /**
     * Sets the complex part of the complex number
     * @param complexPart The new complex part
     */
    public void setComplexPart(final double complexPart) {
        this.complexPart = complexPart;
    }

    /**
     * Sets this complex number to the sum of itself and another complex number
     * @param toAdd The complex number to add
     */
    public void add(final ComplexNumber toAdd){
        this.realPart += toAdd.realPart;
        this.complexPart += toAdd.complexPart;
    }

    /**
     * Sets this complex number to the product of itself and another complex number
     * @param toMultiply The complex number to multiply with
     */
    public void multiply(final ComplexNumber toMultiply){
        double newRealPart = this.realPart* toMultiply.realPart - this.complexPart * toMultiply.complexPart;
        double newComplexPart = this.complexPart * toMultiply.realPart + this.realPart * toMultiply.complexPart;
        this.realPart = newRealPart;
        this.complexPart = newComplexPart;
    }

    /**
     * Multiplies this complex number with itself the amount of times specified
     * @param exponent The amount of times to multiply this complex number by itself
     */
    public void power(final int exponent){
        ComplexNumber initial = this.copy();
        for (int i = 1; i < exponent; i++) {
            this.multiply(initial);
        }
    }

    /**
     * Returns a copy of this complex number
     * @return A copy of this complex number
     */
    public ComplexNumber copy(){
        return new ComplexNumber(this.realPart, this.complexPart);
    }

    /**
     * Gets the distance of this complex number from the origin
     * @return The distance to the origin
     */
    public double getDistanceFromOrigin(){
        return Math.sqrt(this.realPart * this.realPart + this.getComplexPart() * this.getComplexPart());
    }

    @Override
    public String toString() {
        return "ComplexNumbers.ComplexNumber{" +
                "realPart=" + realPart +
                ", complexPart=" + complexPart +
                '}';
    }
}
