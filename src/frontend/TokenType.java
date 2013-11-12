package frontend;

import java.util.HashMap;

public enum TokenType {
    // Reserved words (Does not go into symbol table)
    AND, COND, CONS, DEFINE, DIVIDE("/"), ELSE, IF, LAMBDA, LET, LETREC, LETSTAR("let*"),
    MAP, MINUS("-"), MULTIPLY("*"), NOT, NULL("null?"), OR, PLUS("+"),

    // Reserved special symbols (Does not go into symbol table)
    LEFT_PAREN("("), RIGHT_PAREN(")"), QUOTE("'"),

    // Others (Does not go into symbol table)
    INTEGER, REAL, STRING, RESERVED_SYMBOL, REGULAR_SYMBOL,

    // Error handling
    ERROR, END_OF_FILE;

    private static final int FIRST_RESERVED_WORD_INDEX = AND.ordinal();
    private static final int LAST_RESERVED_WORD_INDEX = PLUS.ordinal();

    private static final int FIRST_RESERVED_SYMBOL_INDEX = LEFT_PAREN.ordinal();
    private static final int LAST_RESERVED_SYMBOL_INDEX  = QUOTE.ordinal();

    private String text;  // token text

    TokenType() {
        this.text = this.toString().toLowerCase();
    }

    TokenType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static HashMap<String, TokenType> ALL_SYMBOLS = new HashMap<String, TokenType>();
    public static HashMap<String, TokenType> RESERVED_WORDS = new HashMap<String, TokenType>();
    public static HashMap<String, TokenType> RESERVED_SYMBOLS = new HashMap<String, TokenType>();
    static {
        TokenType values[] = TokenType.values();
        for (int i = FIRST_RESERVED_WORD_INDEX; i <= LAST_RESERVED_WORD_INDEX; ++i) {
            RESERVED_WORDS.put(values[i].getText().toLowerCase(), values[i]);
        }

        values = TokenType.values();
        for (int i = FIRST_RESERVED_SYMBOL_INDEX; i <= LAST_RESERVED_SYMBOL_INDEX; ++i) {
            RESERVED_SYMBOLS.put(values[i].getText().toLowerCase(), values[i]);
        }

        ALL_SYMBOLS.putAll(RESERVED_WORDS);
        ALL_SYMBOLS.putAll(RESERVED_SYMBOLS);
    }
}
