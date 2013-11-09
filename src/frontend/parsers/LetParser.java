package frontend.parsers;

import frontend.Parser;
import frontend.Scanner;
import intermediate.IntermediateCode;

import java.io.IOException;

public class LetParser extends Parser {
    public LetParser(Scanner scanner) {
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
