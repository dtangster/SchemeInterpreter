package frontend;


import java.io.IOException;

public class EofToken extends Token {
    /**
     * Constructor.
     * @param source the source from where to fetch subsequent characters.
     * @throws Exception if an error occurred.
     */
    public EofToken(Source source) throws IOException {
        super(source);
        type = TokenType.END_OF_FILE;
    }

    /**
     * Do nothing.  Do not consume any source characters.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    protected void extract(Source source) throws IOException {}
}
