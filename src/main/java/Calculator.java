import java.util.*;

public class Calculator {

    public Calculator() {
    }

    public double run(String expression) {

        double result = 0;
        try {
            result = removeParentheses(expression);
            System.out.println("결과: " + result);
        } catch (IllegalArgumentException e) {
            System.err.println("오류: " + e.getMessage());
        }
        return result;
    }

    private double calculate(List<String> expression) {
        List<String> list = new ArrayList<>(expression);

        // 괄호만 있을 경우 처리
        if (list.size() == 1) return Double.parseDouble(list.get(0));

        int idx = 1;    // 기본으로 2번째 값이 기호이기 때문
        // *, /가 있는지 찾기
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("*") || list.get(i).equals("/")) {
                idx = i;
                break;
            }
        }

        // 기호를 기준으로 앞뒤 숫자를 계산
        String num1 = list.get(idx - 1);
        String op = list.get(idx);
        String num2 = list.get(idx + 1);
        double result = switch (op) {
            case "+" -> Double.parseDouble(num1) + Double.parseDouble(num2);
            case "-" -> Double.parseDouble(num1) - Double.parseDouble(num2);
            case "*" -> Double.parseDouble(num1) * Double.parseDouble(num2);
            case "/" -> Double.parseDouble(num1) / Double.parseDouble(num2);
            default -> throw new IllegalArgumentException(op);
        };

        // 연산할 것이 없다면 return
        if (list.size() == 3) return result;

        // 연산이 남았다면 합쳐서 재귀
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < idx - 1) {
                System.out.println(i + " " + idx + " " + list.get(i));
                tmp.add(list.get(i));
            } else if (i == idx) tmp.add(String.valueOf(result));
            else if (i > idx + 1) {
                System.out.println(i + " " + idx + " " + list.get(i));
                tmp.add(list.get(i));
            }
        }
        return calculate(tmp);

    }

    private double removeParentheses(String expression) {
        String tmp = expression;
        int startInx = -1;
        int endInx = -1;
        // 가장 안쪽 괄호를 찾기
        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) == '(')
                startInx = i;
            else if (tmp.charAt(i) == ')') {
                endInx = i;
                break;
            }
        }
        double res;
        if (startInx != -1 && endInx != -1) {   // 괄호가 있다면
            res = calculate(Arrays.asList(expression.substring(startInx + 1, endInx).split(" ")));  // 괄호 안 연산
            tmp = expression.substring(0, startInx) + res + expression.substring(endInx + 1);
            return removeParentheses(tmp);
        }
        return calculate(Arrays.asList(expression.split(" ")));
    }

}
