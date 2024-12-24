package util;

import static enums.Operator.*;
import static enums.Parenthesis.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<String> parse(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder number = new StringBuilder();
        boolean lastWasOperator = true;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                number.append(c);
                lastWasOperator = false;
                continue;
            }

            if (c == MINUS.getSymbol()) {
                if (lastWasOperator) {
                    number.append(c);
                    continue;
                }
                addToken(tokens, number);
                tokens.add(String.valueOf(c));
                lastWasOperator = true;
                continue;
            }

            if (isOperator(c) || isParenthesis(c)) {
                addToken(tokens, number);
                tokens.add(String.valueOf(c));
                lastWasOperator = (c != RIGHT_BRACKET.getSymbol());
                continue;
            }
        }

        addToken(tokens, number);
        return tokens;
    }

    private void addToken(List<String> tokens, StringBuilder number) {
        if (number.length() == 0)
            return;
        tokens.add(number.toString());
        number.setLength(0);
    }

    private boolean isOperator(char c) {
        return c == PLUS.getSymbol() || c == MINUS.getSymbol() || c == MUL.getSymbol() || c == DIV.getSymbol();
    }

    private boolean isParenthesis(char c) {
        return c == LEFT_BRACKET.getSymbol() || c == RIGHT_BRACKET.getSymbol();
    }
}
