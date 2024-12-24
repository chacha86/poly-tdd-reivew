package util;

import static enums.Operator.*;
import static enums.Parenthesis.*;

import java.util.ArrayList;
import java.util.List;

import enums.Operator;

public class Evaluator {

    public int operate(List<String> tokens) {
        try {
            // () 위치
            int leftIdx = findLeftBracket(tokens); // ( 위치
            int rightIdx = findRightBracket(tokens, leftIdx); // ) 위치
            List<String> newTokens = null;

            // () 내의 연산 결과
            int res = operate(tokens.subList(leftIdx + 1, rightIdx));

            // () 양 옆에 식이 없을 경우
            if (leftIdx == 0 && rightIdx == tokens.size() - 1) {
                return res;
            }

            // () 양 옆에 식이 있을 경우
            if (leftIdx > 0 && rightIdx < tokens.size() - 1) {
                // ()에 부호가 붙은 경우
                if (leftIdx % 2 != 0) {
                    res = calcSign(res, tokens.get(leftIdx - 1));
                    newTokens = addResToCenter(tokens, res, leftIdx - 1, rightIdx);
                }
                // ()에 부호가 붙지 않은 경우
                else {
                    newTokens = addResToCenter(tokens, res, leftIdx, rightIdx);
                }
            }

            // () 왼쪽에만 식이 있을 경우
            if (leftIdx > 0 && rightIdx == tokens.size() - 1) {
                // ()에 부호가 붙은 경우
                if (leftIdx % 2 != 0) {
                    res = calcSign(res, tokens.get(leftIdx - 1));
                    newTokens = addResToRight(tokens, res, leftIdx - 1);
                }
                // ()에 부호가 붙지 않은 경우
                else {
                    newTokens = addResToRight(tokens, res, leftIdx);
                }
            }

            // () 오른쪽에만 식이 있을 경우
            if (leftIdx == 0 && rightIdx < tokens.size() - 1) {
                newTokens = addResToLeft(tokens, res, rightIdx);
            }

            if (newTokens == null) {
                throw new IllegalStateException();
            }

            return operate(newTokens);
        } catch (NullPointerException e) { // ()가 없는 경우
            return evaluate(tokens);
        }
    }

    public int calcSign(int res, String op) {
        if (op.equals(MINUS.getString())) {
            return -1 * res;
        }
        if (op.equals(PLUS.getString())) {
            return 1 * res;
        }
        throw new IllegalArgumentException();
    }

    public int findLeftBracket(List<String> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals(LEFT_BRACKET.getString()))
                return i;
        }
        throw new NullPointerException();
    }

    public int findRightBracket(List<String> tokens, int leftIdx) {
        int depth = 1;
        for (int i = leftIdx + 1; i < tokens.size(); i++) {
            if (tokens.get(i).equals(LEFT_BRACKET.getString()))
                depth++;
            else if (tokens.get(i).equals(RIGHT_BRACKET.getString()))
                depth--;
            if (depth == 0)
                return i;
        }
        throw new NullPointerException();
    }

    /**
     * ()가 없는 식의 연산
     */
    public int evaluate(List<String> tokens) {
        List<String> intermediateTokens = evaluatePriority(tokens, MUL.getString(), DIV.getString());

        return evaluatePriority(intermediateTokens, PLUS.getString(), MINUS.getString())
            .stream().mapToInt(Integer::parseInt).sum();
    }

    /**
     * 우선순위에 따른 연산
     */
    public List<String> evaluatePriority(List<String> tokens, String... operators) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < tokens.size()) {
            String token = tokens.get(i);

            if (!isOperator(token, operators)) {
                result.add(token);
                i++;
                continue;
            }

            String prev = result.remove(result.size() - 1);
            String next = tokens.get(++i);
            int intermediateResult = calculate(
                Integer.parseInt(prev),
                Integer.parseInt(next),
                Operator.from(token)
            );

            result.add(String.valueOf(intermediateResult));
            i++;
        }

        return result;
    }

    public List<String> addResToCenter(List<String> tokens, int res, int leftIdx, int rightIdx) {
        List<String> newTokens = new ArrayList<>();
        List<String> leftTokens = tokens.subList(0, leftIdx);
        List<String> rightTokens = tokens.subList(rightIdx + 1, tokens.size());

        newTokens.addAll(leftTokens);
        newTokens.add(String.valueOf(res));
        newTokens.addAll(rightTokens);

        return newTokens;
    }

    public List<String> addResToLeft(List<String> tokens, int res, int rightIdx) {
        List<String> newTokens = new ArrayList<>(tokens.subList(rightIdx + 1, tokens.size()));
        newTokens.addFirst(String.valueOf(res));
        return newTokens;
    }

    public List<String> addResToRight(List<String> tokens, int res, int leftIdx) {
        List<String> newTokens = new ArrayList<>(tokens.subList(0, leftIdx));
        newTokens.addLast(String.valueOf(res));
        return newTokens;
    }

    public int calculate(int a, int b, Operator operator) {
        if (operator == PLUS) {
            return a + b;
        }

        if (operator == MINUS) {
            return a - b;
        }

        if (operator == MUL) {
            return a * b;
        }

        if (operator == DIV) {
            return a / b;
        }

        throw new IllegalArgumentException();
    }
}
