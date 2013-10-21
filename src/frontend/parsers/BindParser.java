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
        switch (token.getType()) {
            case IDENTIFIER:

                break;
        }
    }
}
