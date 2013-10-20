package frontend;

import java.io.IOException;

public class Token {
    protected TokenType type;  // language-specific token type
    protected String text;     // token text
    protected Object value;    // token value
    protected Source source;   // source
    protected int lineNum;     // line number of the token's source line
    protected int position;    // position of the first token character

    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public Token(Source source) throws IOException {
        this.source = source;
        this.lineNum = source.getLineNum();
        this.position = source.getPosition();
        extract();
    }

    /**
     * Getter
     * @return the token type
     */
    public TokenType getType()
    {
        return type;
    }

    /**
     * Getter.
     * @return the token text.
     */
    public String getText()
    {
        return text;
    }

    /**
     * Getter.
     * @return the token value.
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * Getter.
     * @return the source line number.
     */
    public int getLineNumber()
    {
        return lineNum;
    }

    /**
     * Getter.
     * @return the position.
     */
    public int getPosition()
    {
        return position;
    }

    /**
     * Default method to extract only one-character tokens from the source.
     * Subclasses can override this method to construct language-specific
     * tokens.  After extracting the token, the current source line position
     * will be one beyond the last token character.
     * @throws Exception if an error occurred.
     */
    protected void extract() throws IOException {
        text = Character.toString(currentChar());
        nextChar();  // consume current character
    }

    /**
     * Call the source's currentChar() method.
     * @return the current character from the source.
     * @throws Exception if an error occurred.
     */
    protected char currentChar() throws IOException {
        return source.currentChar();
    }

    /**
     * Call the source's nextChar() method.
     * @return the next character from the source after moving forward.
     * @throws Exception if an error occurred.
     */
    protected char nextChar() throws IOException {
        return source.nextChar();
    }

    /**
     * Call the source's peekChar() method.
     * @return the next character from the source without moving forward.
     * @throws Exception if an error occurred.
     */
    protected char peekChar() throws IOException {
        return source.peekChar();
    }
}
