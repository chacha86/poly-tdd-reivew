package org.calc;

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        final String expression = "((3 + 5) * 5 + -10) * 10 / 5";
        int result = calc.calculateExpression(expression);
        System.out.println(result);
    }
}