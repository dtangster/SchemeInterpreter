package frontend;

import java.io.IOException;
import static frontend.Source.EOF;
import static frontend.Source.EOL;

public class Scanner {
    private Source source;
    private Token currentToken;

    public Scanner(Source source) {
        this.source = source;
    }

    public Token extractToken() throws IOException {
        skipWhiteSpace();

        // Temporary until we find out what to do
        return new Token();
    }

    private void skipWhiteSpace() throws IOException {
        char currentChar = currentChar();

        while (Character.isWhitespace(currentChar) || (currentChar == ';')) {
            // Start of a comment?
            if (currentChar == ';') {
                do {
                    currentChar = nextChar();  // consume comment characters
                } while ((currentChar != EOL) && (currentChar != EOF));
                //source.readLine();
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
