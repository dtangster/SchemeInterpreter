package frontend.parsers;

import frontend.Parser;
import frontend.Scanner;
import frontend.Token;
import frontend.TokenType;
import intermediate.IntermediateCode;
import intermediate.IntermediateCode;

import java.io.IOException;

public class ListParser extends Parser {
    public ListParser(Scanner scanner) {
        super(scanner);
    }

    public IntermediateCode parse(Token token) throws IOException {
        //try {
            while (token.getType() == TokenType.LEFT_PAREN) {
                //TODO: Do something here

                token = currentToken();
            }

            //TODO: Temporary until we find out what to do
            return null;
        //}

        //TODO: Removing this until the parsing is implemented
        /*
        catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
}
