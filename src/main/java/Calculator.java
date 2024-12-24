public class Calculator {

    public int run(String expression) {
        expression = expression.replaceAll(" ", "");
        return calculate(expression);
    }

    private boolean isEnclosed(String expression) {
        int grouping = 0 ;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                grouping++;
            } else if (expression.charAt(i) == ')') {
                grouping--;
                if (grouping == 0 && i < expression.length() - 1) {
                    return false;
                }
            }
        }
        return true;
    }
    public int calculate(String expr) {
        if (expr.startsWith("(") && expr.endsWith(")") && isEnclosed(expr)) {
            expr = expr.substring(1, expr.length() - 1);
            return calculate(expr);
        }
        int grouping = 0;
        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (c == '(') {
                grouping++;
            } else if (c == ')') {
                grouping--;
            } else if (grouping == 0 && (c == '+' || c == '-')) {
                if (c == '-' && i > 0 && (expr.charAt(i - 1) == '*' || expr.charAt(i - 1) == '/')) continue;
                int left = calculate(expr.substring(0, i));
                int right = calculate(expr.substring(i + 1));
                return c == '+' ? left + right : left - right;
            }
        }
        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (c == '(') {
                grouping++;
            } else if (c == ')') {
                grouping--;
            } else if (grouping == 0 && (c == '*' || c == '/')) {
                int left = calculate(expr.substring(0, i));
                int right = calculate(expr.substring(i + 1));
                return c == '*' ? left * right : left / right;
            }
        }
        return expr.isBlank() ? 0 : Integer.parseInt(expr);
    }
}
