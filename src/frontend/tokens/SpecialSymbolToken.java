package frontend.tokens;

import frontend.Source;
import frontend.Token;

import java.io.IOException;

import static frontend.TokenType.SPECIAL_SYMBOLS;
import static frontend.TokenType.*;

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
                type = LEFT_PAREN;
                text = Character.toString(currentChar);
                value = currentChar;
                break;
            case ')':
                type = RIGHT_PAREN;
                text = Character.toString(currentChar);
                value = currentChar;
                break;
            case '+':
                type = PLUS;
                text = Character.toString(currentChar);
                value = currentChar;
                break;
            case '-':
                type = MINUS;
                text = Character.toString(currentChar);
                value = currentChar;
                break;
            case '*':
                type = MULTIPLICATION;
                text = Character.toString(currentChar);
                value = currentChar;
                break;
            case '/':
                type = DIVISIOR;
                text = Character.toString(currentChar);
                value = currentChar;
                break;
            case '\'':
                nextChar();  // consume character
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
