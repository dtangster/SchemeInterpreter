package frontend;

import intermediate.SymbolTableEntry;

import java.io.IOException;

public class Token {
    protected TokenType type;  // language-specific token type
    protected String text;     // token text
    protected Object value;    // token value
    protected Source source;   // source
    protected int lineNum;     // line number of the token's source line
    protected int position;    // position of the first token character
    private SymbolTableEntry en;

    public Token(Source source) throws IOException {
        this.source = source;
        this.lineNum = source.getLineNum();
        this.position = source.getPosition();
        extract();
    }

    public void setEntry(SymbolTableEntry en)
    {
        this.en = en;
    }

    public TokenType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public Object getValue() {
        return value;
    }

    public int getLineNumber() {
        return lineNum;
    }

    public int getPosition() {
        return position;
    }

    protected void extract() throws IOException {
        text = Character.toString(currentChar());
        nextChar();  // consume current character
    }

    protected char currentChar() throws IOException {
        return source.currentChar();
    }

    protected char nextChar() throws IOException {
        return source.nextChar();
    }

    protected char peekChar() throws IOException {
        return source.peekChar();
    }
}
