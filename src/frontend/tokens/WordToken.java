package frontend.tokens;

import frontend.*;

import static frontend.TokenType.*;

public class WordToken extends Token {

    public WordToken(Source source)
            throws Exception
    {
        super(source);
    }

    /**
     * Extract a Scheme word token from the source.
     * @throws Exception if an error occurred.
     */
    protected void extract()
            throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        char currentChar = currentChar();

        // Get the word characters (letter or digit).  The scanner has
        // already determined that the first character is a letter.
        while (Character.isLetterOrDigit(currentChar)) {
            textBuffer.append(currentChar);
            currentChar = nextChar();  // consume character
        }

        text = textBuffer.toString();

        // Is it a reserved word or an identifier?
        type = (RESERVED_WORDS.contains(text.toLowerCase()))
                ? TokenType.valueOf(text.toUpperCase())  // reserved word
                : IDENTIFIER;                                  // identifier
    }

}
