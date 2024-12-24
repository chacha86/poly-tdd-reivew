package org.example;

public class Calc {
    public static int run(String expression) {
        class Evaluator {
            private int i = 0;

            private int getNumber(String expr) {
                int number = 0;
                int sign = 1;
                if (expr.charAt(i) == '-') {
                    sign = -1;
                    i++;
                }
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    number = number * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                return sign * number;
            }

            private int getTerm(String expr) {
                if (expr.charAt(i) == '(') {
                    i++;
                    int result = parseExpression(expr);
                    i++;
                    return result;
                } else {
                    return getNumber(expr);
                }
            }

            private int parseExpression(String expr) {
                int result = parseTerm(expr);
                while (i < expr.length() && (expr.charAt(i) == '+' || expr.charAt(i) == '-')) {
                    char op = expr.charAt(i);
                    i++;
                    if (op == '+') {
                        result += parseTerm(expr);
                    } else if (op == '-') {
                        result -= parseTerm(expr);
                    }
                }
                return result;
            }

            private int parseTerm(String expr) {
                int result = getFactor(expr);
                while (i < expr.length() && (expr.charAt(i) == '*' || expr.charAt(i) == '/')) {
                    char op = expr.charAt(i);
                    i++;
                    if (op == '*') {
                        result *= getFactor(expr);
                    } else if (op == '/') {
                        result /= getFactor(expr);
                    }
                }
                return result;
            }

            private int getFactor(String expr) {
                if (expr.charAt(i) == '-') {
                    i++;
                    return -getFactor(expr);
                } else {
                    return getTerm(expr);
                }
            }

            public int evaluate(String expr) {
                return parseExpression(expr);
            }
        }

        expression = expression.replaceAll(" ", "");
        Evaluator evaluator = new Evaluator();
        return evaluator.evaluate(expression);
    }
}
