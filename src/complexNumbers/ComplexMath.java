package complexNumbers;

public class ComplexMath {
    private ComplexMath(){}

    public static ComplexNumber add(ComplexNumber a, ComplexNumber b){
        return new ComplexNumber(a.getRealPart() + b.getRealPart(),
                a.getComplexPart() + b.getComplexPart());
    }

    public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b){
        return new ComplexNumber(
                a.getRealPart() * b.getRealPart() - a.getComplexPart() * b.getComplexPart(),
                a.getComplexPart() * b.getRealPart() + a.getRealPart() * b.getComplexPart()
        );
    }
}
