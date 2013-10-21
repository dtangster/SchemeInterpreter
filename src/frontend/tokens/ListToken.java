package frontend.tokens;

import frontend.Source;
import frontend.Token;

import java.io.IOException;

public class ListToken extends Token {
    public ListToken(Source source) throws IOException {
        super(source);
    }

    protected void extract() throws IOException {
        //TODO: Need to implement this. This token will rely on ALL the other tokens.
    }
}
