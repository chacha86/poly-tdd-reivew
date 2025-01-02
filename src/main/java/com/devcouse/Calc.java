package com.devcouse;

public class Calc {

    public int run(String expression) {

        expression = expression.replaceAll(" ", "");

        return calculator(expression);
    }

    private int calculator(String expression) {
        if (expression.contains("(")) {
            int startIndex = expression.lastIndexOf("(");
            int endIndex = findClosingParenthesis(expression, startIndex);

            int inner = calculator(expression.substring(startIndex + 1, endIndex));
            expression = expression.substring(0, startIndex) + inner + expression.substring(endIndex + 1);

            return calculator(expression);
        }

        int index = findLowestOperatorIndex(expression);
        if (index == -1) {
            return Integer.parseInt(expression);
        }

        int left = calculator(expression.substring(0, index));
        int right = calculator(expression.substring(index+1));

        return basicOperations(left, right, expression.charAt(index));
    }

    private int findClosingParenthesis(String expression, int openIndex) {
        int balance = 0;
        for (int i = openIndex; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') balance++;
            if (expression.charAt(i) == ')') balance--;
            if (balance == 0) return i;
        }
        throw new IllegalArgumentException("괄호가 올바르지 않습니다: " + expression);
    }

    private int findLowestOperatorIndex(String expression) {
        int index = -1;
        int minPriotiry = Integer.MAX_VALUE;

        for (int i=expression.length()-1; i>=0; i--) {
            char c = expression.charAt(i);

            if (c>'0' && c<'9') continue;
            if (c=='-') {
                if (i==0) continue;

                char preC = expression.charAt(i-1);
                if (preC == '(' || preC == '+' || preC == '-' || preC == '*' || preC == '/') continue;
            }
            if (getPriority(c) < minPriotiry) {
                minPriotiry = getPriority(c);
                index = i;
            }
        }

        return index;
    }

    private int getPriority(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }

        return Integer.MAX_VALUE;
    }

    private int basicOperations(int left, int right, char o) {
        int result = 0;

        switch (o) {
            case '+' -> result = left + right;
            case '-' -> result = left - right;
            case '*' -> result = left * right;
            case '/' -> result = left / right;
        }

        return result;
    }
}