package frontend;

public enum TokenType {
    // Reserved words
    AND("and"), BEGIN("begin"), COND("cond"), DEFINE("define"), ELSE("else"), IF("if"),
    LAMBDA("lambda"),LET("let"), LETREC("letrec"), LETSTAR("let*"), NOT("not"), OR("or"),

    // Special Symbols
    QUOTE("'"), EQUALS("="), LESS_THAN("<"), LESS_EQUALS("<="), GREATER_EQUALS(">="),
    GREATER_THAN(">"), LEFT_PAREN("("), RIGHT_PAREN(")"),

    // Others
    IDENTIFIER, INTEGER, REAL, STRING,
    ERROR, END_OF_FILE;
}
