package enums;

public enum Parenthesis {
    LEFT_BRACKET('('),
    RIGHT_BRACKET(')');

    private final char symbol;

    Parenthesis(char symbol) {
        this.symbol = symbol;
    }

    public static Parenthesis from(char c) {
        for (Parenthesis p : values()) {
            if (p.symbol == c)
                return p;
        }

        throw new IllegalArgumentException();
    }

    public static boolean isParenthesis(String parenthesis) {
        return LEFT_BRACKET.getString() == parenthesis || RIGHT_BRACKET.getString() == parenthesis;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getString() {
        return String.valueOf(symbol);
    }
}
