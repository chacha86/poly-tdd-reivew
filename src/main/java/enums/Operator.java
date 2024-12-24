package enums;

public enum Operator {
    PLUS('+'),
    MINUS('-'),
    MUL('*'),
    DIV('/');

    private final char symbol;

    Operator(char symbol) {
        this.symbol = symbol;
    }

    public static Operator from(char c) {
        for (Operator op : values()) {
            if (op.symbol == c)
                return op;
        }

        throw new IllegalArgumentException();
    }

    public static Operator from(String s) {
        for (Operator op : values()) {
            if (op.getString().equals(s))
                return op;
        }

        throw new IllegalArgumentException();
    }

    public static boolean isOperator(String token, String... operators) {
        for (String operator : operators) {
            if (token.equals(operator))
                return true;
        }
        return false;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getString() {
        return String.valueOf(symbol);
    }
}
