package util;

import java.util.List;

public class Calculator {

    public int plus(int num1, int num2) {
        return num1 + num2;
    }

    public int minus(int num1, int num2) {
        return num1 - num2;
    }

    public int multiply(int num1, int num2) {
        return num1 * num2;
    }

    public int divide(int num1, int num2) {
        return num1 / num2;
    }

    public int calc(String expression) {
        Parser parser = new Parser();
        List<String> tokens = parser.parse(expression);

        Evaluator evaluator = new Evaluator();
        return evaluator.operate(tokens);
    }
}
