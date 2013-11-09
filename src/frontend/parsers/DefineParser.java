package frontend.parsers;

import frontend.Parser;
import frontend.Scanner;
import intermediate.IntermediateCode;

import java.io.IOException;

public class DefineParser extends Parser {
    public DefineParser(Scanner scanner) {
        super(scanner);
    }

    @Override
    public IntermediateCode parse() throws IOException {
        return null;
    }

    @Override
    public IntermediateCode parseList() {
        return null; // Placeholder
    }
}
