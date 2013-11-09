package frontend.parsers;

import frontend.Parser;
import frontend.Scanner;
import intermediate.IntermediateCode;
import intermediate.SymbolTableStack;

import java.io.IOException;

public class DefineParser extends Parser {
    public DefineParser(SymbolTableStack symbolTableStack, Scanner scanner) {
        super(symbolTableStack, scanner);
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
