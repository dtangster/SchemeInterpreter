package frontend.tokens;

import frontend.Source;
import frontend.Token;
import frontend.TokenType;

import java.io.IOException;

public class SymbolToken extends Token {
    public SymbolToken(Source source) throws IOException {
        super(source);
    }

    protected void extract() throws IOException {
        char currentChar = currentChar();

        text = Character.toString(currentChar);
        type = null;

        if (TokenType.RESERVED_SYMBOLS.containsKey(text)) {
            type = TokenType.RESERVED_SYMBOLS.get(text);
            nextChar();
        }
        else {
            type = TokenType.REGULAR_SYMBOL;
            nextChar();
        }
    }
}