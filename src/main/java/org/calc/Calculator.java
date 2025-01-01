package org.calc;

import java.util.List;

public class Calculator {

    public int add(final int num1, final int num2) {
        return num1 + num2;
    }

    public int subtract(final int num1, final int num2) {
        return num1 - num2;
    }

    public int multiply(final int num1, final int num2) {
        return num1 * num2;
    }

    public int divide(final int num1, final int num2) {
        return num1 / num2;
    }

    public int calculateExpression(final String expression) {
        String trimedExpression = removeSpaces(expression);
        StringBuilder expressionBuilder = new StringBuilder(trimedExpression);
        StringBuilder numberBuilder = new StringBuilder();
        int result = 0;
        int lastNumber = 0;
        char lastOperator = ' ';
        boolean positive = true; // 현재 계산중인 항의 positive 여부
        boolean isPrevTokenOperator = true;
        int bracketEndIndex = trimedExpression.indexOf(')');
        int bracketStartIndex;

        // 괄호가 존재하지 않을 때까지 내부 괄호 계산식 계산 후 대체
        while (bracketEndIndex != -1) {
            bracketEndIndex = trimedExpression.indexOf(')');
            bracketStartIndex = trimedExpression.lastIndexOf('(', bracketEndIndex);
            // 괄호 앞에 숫자가 있는 경우
            if (0 < bracketStartIndex && !isOperator(expressionBuilder.charAt(bracketStartIndex - 1))) {
                expressionBuilder.insert(bracketStartIndex, '*');
                bracketStartIndex++;
                bracketEndIndex++;
            }
            if (bracketStartIndex != -1) {
                expressionBuilder.replace(
                        bracketStartIndex,
                        bracketEndIndex + 1,
                        String.valueOf(calculateExpression(expressionBuilder.substring(bracketStartIndex + 1, bracketEndIndex))));
                trimedExpression = expressionBuilder.toString();
            }
        }

        // 수식 순회
        for (char token : trimedExpression.toCharArray()) {
            if (isOperator(token)) {
                if ((isPrevTokenOperator) && token == '-') { // + -10 , - -10
                    positive = !positive;
                    continue;
                }
                isPrevTokenOperator = true;

                switch (token) {
                    case '+':
                    case '-': // 하나의 항이 끝남
                        result = positive ? (result + calculate(lastOperator, lastNumber, extractInt(numberBuilder)))
                                : (result - calculate(lastOperator, lastNumber, extractInt(numberBuilder)));
                        lastNumber = 0;
                        positive = true;
                        break;
                    case '*':
                    case '/': // 항이 계속됨
                        if (lastOperator == '*' || lastOperator == '/') {
                            lastNumber = calculate(lastOperator, lastNumber, extractInt(numberBuilder));
                            break;
                        }
                        if (lastOperator == '+') {
                            positive = true;
                            lastNumber = extractInt(numberBuilder);
                            break;
                        }
                        if (lastOperator == '-') {
                            positive = false;
                            lastNumber = extractInt(numberBuilder);
                            break;
                        }
                        lastNumber = extractInt(numberBuilder);
                        break;
                }
                lastOperator = token;
                continue;
            }
            // 숫자인 경우
            numberBuilder.append(token);
            isPrevTokenOperator = false;
        }


        result = positive ? (result + calculate(lastOperator, lastNumber, extractInt(numberBuilder)))
                : (result - calculate(lastOperator, lastNumber, extractInt(numberBuilder)));
        return result;
    }

    private int extractInt(final StringBuilder numberBuilder) {
        int extracted = Integer.parseInt(numberBuilder.toString());
        numberBuilder.setLength(0);
        return extracted;
    }

    private boolean isOperator(final char letter) {
        return List.of('+', '-', '*', '/').contains(letter);
    }

    private Integer calculate(final char operator, final int num1, final int num2) {
        if (operator == '+') {
            return add(num1, num2);
        }
        if (operator == '-') {
            return subtract(num1, num2);
        }
        if (operator == '*') {
            return multiply(num1, num2);
        }
        if (operator == ' ') {
            return num2;
        }
        if (num2 != 0) {
            return divide(num1, num2);
        }
        throw new ArithmeticException("0으로 나눌 수 없습니다");
    }

    private static String removeSpaces(final String formula) {
        return formula.replace(" ", "");
    }
}
