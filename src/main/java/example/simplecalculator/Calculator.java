package example.simplecalculator;

public class Calculator {

    public double add (double a, double b) {
        if(a < 0 || b < 0) {
            throw new IllegalArgumentException("Numbers must be positive");
        }
        return a + b;
    }

}
