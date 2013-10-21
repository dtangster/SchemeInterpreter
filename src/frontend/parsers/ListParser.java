package frontend.parsers;

import frontend.Parser;
import frontend.Scanner;
import frontend.Token;
import intermediate.IntermediateCode;

import java.io.IOException;

public class ListParser extends Parser {
    public ListParser(Scanner scanner) {
        super(scanner);
    }

    public IntermediateCode parse() throws IOException {
        Parser parser = new Parser(scanner);
        Token currentToken = nextToken(); // Consume (

        switch (currentToken.getType()) {
            case LEFT_PAREN:
                ListParser listParser = new ListParser(scanner);
                intermediateCode.setCar(listParser.parse());
                intermediateCode.setCdr(parser.parse());
                break;

            // Trying things
            case LAMBDA:

            case DEFINE:
                BindParser bindParser = new BindParser(scanner);
                intermediateCode.setCar(bindParser.parse());
                intermediateCode.setCdr(parser.parse());
                break;
            case IDENTIFIER:
                break;
        }

        currentToken = currentToken();
        return intermediateCode;
    }
}
