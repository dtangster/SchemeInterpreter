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
        text = Character.toString(currentChar());
        type = TokenType.SYMBOL;
        nextChar();  // consume current character
    }
}
