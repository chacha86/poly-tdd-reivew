package Calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calc {

    public int run(String expression) {

        if(!expression.contains("(")) {
            return calculateBasic(expression);
        }

        int startIndex = expression.lastIndexOf("(");
        int endIndex = expression.indexOf(")", startIndex);

        String calculateResult = Integer.toString(calculateBasic(expression.substring(startIndex + 1, endIndex)));
        String newExpression = expression.substring(0, startIndex) + calculateResult + expression.substring(endIndex + 1);

        return run(newExpression);
    }

    private int calculateBasic(String expression) {
        List<String> tokens = new ArrayList<>(Arrays.asList(expression.split(" ")));

        // 곱셈 나눗셈 우선 계산
        tokens = tokensCalculate(tokens, "*", "/");
        // 덧셈 뺄샘 이후 계산
        tokens = tokensCalculate(tokens, "+", "-");

        return Integer.parseInt(tokens.get(0));
    }

    private List<String> tokensCalculate(List<String> tokens, String op1, String op2) {
        for(int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i).equals(op1) || tokens.get(i).equals(op2)) {
                int result = calculate(tokens.get(i - 1), tokens.get(i), tokens.get(i + 1));
                tokens.set(i - 1, Integer.toString(result));
                tokens.remove(i);
                tokens.remove(i);
                i--;
            }
        }

        return tokens;
    }

    private int calculate(String num1, String op, String num2) {
        int a = Integer.parseInt(num1);
        int b = Integer.parseInt(num2);

        int res = 0;

        switch (op) {
            case "*" -> res = a * b;
            case "/" -> res = a / b;
            case "+" -> res = a + b;
            case "-" -> res = a - b;
        }

        return res;
    }
}