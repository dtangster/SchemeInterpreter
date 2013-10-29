package frontend.tokens;

import frontend.Source;
import frontend.Token;
import frontend.TokenType;

import java.io.IOException;

public class SpecialSymbolToken extends Token {
    public SpecialSymbolToken(Source source) throws IOException {
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
        else if (TokenType.REGULAR_SYMBOLS.containsKey(text)) {
            type = TokenType.REGULAR_SYMBOLS.get(text);
            nextChar();
        }
    }
}