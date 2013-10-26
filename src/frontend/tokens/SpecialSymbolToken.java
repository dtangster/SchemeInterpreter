package frontend.tokens;

import frontend.Source;
import frontend.Token;

import java.io.IOException;

import static frontend.TokenType.SPECIAL_SYMBOLS;


public class SpecialSymbolToken extends Token {
    public SpecialSymbolToken(Source source) throws IOException {
        super(source);
    }

    protected void extract() throws IOException {
        char currentChar = currentChar();

        text = Character.toString(currentChar);
        type = null;

        switch (currentChar) {
            case '(':
            case ')':
            case '+':
            case '-':
            case '*':
            case '/':
            case '\'':
               nextChar();
                break;

            // < or <=
            case '<': {
                currentChar = nextChar();  // consume '<';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                }

                break;
            }

            // > or >=
            case '>': {
                currentChar = nextChar();  // consume '>';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                }

                break;
            }
        }

        // Set the type if it wasn't an error.
        if (type == null) {
            type = SPECIAL_SYMBOLS.get(text);
        }
    }
}
