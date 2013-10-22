package frontend;

import frontend.tokens.NumberToken;
import frontend.tokens.SpecialSymbolToken;
import frontend.tokens.SymbolToken;
import frontend.tokens.WordToken;

import java.io.IOException;

import static frontend.Source.EOF;

public class Scanner {
    private Source source;
    private Token currentToken;

    public Scanner(Source source) {
        this.source = source;
    }

    public Token extractToken() throws IOException {
        skipWhiteSpace();
        Token token = null;
        char currentChar = currentChar();

        if (currentChar == EOF) {
            token = new EofToken(source);
        }
        else if (Character.isLetter(currentChar)) {
            token = new WordToken(source);
        }
        else if (Character.isDigit(currentChar)) {
            token = new NumberToken(source);
        }
        else if (TokenType.SPECIAL_SYMBOLS
                .containsKey(Character.toString(currentChar))) {
            token = new SpecialSymbolToken(source);
        }
        else {
            token = new SymbolToken(source);
        }

        return token;
    }

    private void skipWhiteSpace() throws IOException {
        char currentChar = currentChar();

        while (Character.isWhitespace(currentChar) || (currentChar == ';')) {
            // Start of a comment?
            if (currentChar == ';') {
                source.readLine();
                currentChar = source.currentChar();
            }

            // Not a comment.
            else {
                currentChar = nextChar();  // consume whitespace character
            }
        }
    }

    public Token currentToken() {
        return currentToken;
    }

    public Token nextToken() throws IOException {
        currentToken = extractToken();
        return currentToken;
    }

    public char currentChar() throws IOException {
        return source.currentChar();
    }

    public char nextChar() throws IOException {
        return source.nextChar();
    }

    public char peekChar() throws IOException {
        return source.peekChar();
    }

    public String toString() {
        return source.toString();
    }

    public int getPosition() {
        return source.getPosition();
    }
}
