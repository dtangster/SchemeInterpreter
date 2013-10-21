package frontend.parsers;

import frontend.Parser;
import frontend.Scanner;
import frontend.Token;
import intermediate.IntermediateCode;

import java.io.IOException;

public class BindParser extends Parser {
    public BindParser(Scanner scanner) {
        super(scanner);
    }

    public IntermediateCode parse(Token token) throws IOException {
        Parser parser = new Parser(scanner);
        token = nextToken(); // Consume (

        switch (token.getType()) {
            case LEFT_PAREN:
                ListParser listParser = new ListParser(scanner);
                intermediateCode.setCar(listParser.parse(token));
                token = currentToken();
                intermediateCode.setCdr(parser.parse());
                break;
            case IDENTIFIER:

                break;
        }

        token = currentToken();
        return intermediateCode;
    }
}
