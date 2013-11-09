package frontend.parsers;

import frontend.Parser;
import frontend.Scanner;
import intermediate.IntermediateCode;

import java.io.IOException;

public class DefineParser extends Parser {
    public DefineParser(Scanner scanner) {
        super(scanner);
    }

    public void parse() throws IOException {

    }

    public IntermediateCode parseList() {
        return null; // Placeholder
    }
}
