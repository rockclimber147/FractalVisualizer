package complexNumbers;

public class ComplexNumber {
    private double realPart;
    private double complexPart;

    public ComplexNumber(double realPart, double complexPart){
        this.realPart = realPart;
        this.complexPart = complexPart;
    }

    public double getRealPart() {
        return realPart;
    }

    public void setRealPart(double realPart) {
        this.realPart = realPart;
    }

    public double getComplexPart() {
        return complexPart;
    }

    public void setComplexPart(double complexPart) {
        this.complexPart = complexPart;
    }

    public void add(ComplexNumber toAdd){
        this.realPart += toAdd.realPart;
        this.complexPart += toAdd.complexPart;
    }

    public void multiply(ComplexNumber toMultiply){
        double newRealPart = this.realPart* toMultiply.realPart - this.complexPart * toMultiply.complexPart;
        double newComplexPart = this.complexPart * toMultiply.realPart + this.realPart * toMultiply.complexPart;
        this.realPart = newRealPart;
        this.complexPart = newComplexPart;
    }

    public void power(int exponent){
        ComplexNumber initial = this.copy();
        for (int i = 1; i < exponent; i++) {
            this.multiply(initial);
        }
    }

    public ComplexNumber copy(){
        return new ComplexNumber(this.realPart, this.complexPart);
    }

    public double getDistanceFromOrigin(){
        return Math.sqrt(this.realPart * this.realPart + this.getComplexPart() * this.getComplexPart());
    }

    public static void main(String[] args) {
        ComplexNumber test = new ComplexNumber(2,3);
        System.out.println(test);
        test.power(5);
        System.out.println(test);
    }

    @Override
    public String toString() {
        return "ComplexNumbers.ComplexNumber{" +
                "realPart=" + realPart +
                ", complexPart=" + complexPart +
                '}';
    }
}
