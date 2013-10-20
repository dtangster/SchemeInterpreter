package frontend;

import java.io.IOException;

public class Scanner {
    private Source source;
    private Token currentToken;

    public Scanner(Source source) {
        this.source = source;
    }

    public Token extractToken() throws IOException {
        skipWhiteSpace();
        return new Token();
    }

    private void skipWhiteSpace() throws IOException {
        char currentChar = currentChar();

        while (Character.isWhitespace(currentChar) || (currentChar == ';')) {
            // Start of a comment?
            if (currentChar == ';') {
                source.readLine();
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
}
