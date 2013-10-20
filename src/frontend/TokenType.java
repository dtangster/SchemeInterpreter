package frontend;

public enum TokenType {
    // Reserved words
    AND, COND, DEFINE, ELSE, IF, LAMBDA, LET, LETREC, LETSTAR("let*"), NOT, OR,

    // Special Symbols
    QUOTE("'"), EQUALS("="), LESS_THAN("<"), LESS_EQUALS("<="), GREATER_EQUALS(">="),
    GREATER_THAN(">"), LEFT_PAREN("("), RIGHT_PAREN(")"),

    // Others
    IDENTIFIER, INTEGER, REAL, STRING,
    ERROR, END_OF_FILE;

    private String text;

    TokenType() {
        this.text = this.toString().toLowerCase();
    }

    TokenType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
