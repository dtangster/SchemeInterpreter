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

    public IntermediateCode parse(Token token) throws IOException {
        switch (token.getType()) {
            case DEFINE:
                BindParser bindParser = new BindParser(scanner);
                intermediateCode = bindParser.parse(token);
                break;
        }

        return intermediateCode;
    }
}
