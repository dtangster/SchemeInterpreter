package frontend;

import frontend.parsers.ListParser;
import intermediate.IntermediateCode;
import intermediate.SymbolTableStack;

import java.io.IOException;

public class Parser {
    protected SymbolTableStack symbolTableStack;
    protected IntermediateCode intermediateCode;
    protected Scanner scanner;

    public Parser(Scanner scanner) {
        symbolTableStack = new SymbolTableStack();
        intermediateCode = new IntermediateCode();
        this.scanner = scanner;
    }

    public IntermediateCode parse() throws IOException {
        try {
            Token token = nextToken();

            switch (token.getType()) {
                case LEFT_PAREN:
                    ListParser listParser = new ListParser(scanner);
                    intermediateCode = listParser.parse();
                    break;
            }

            token = currentToken();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return intermediateCode;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public IntermediateCode getICode() {
        return intermediateCode;
    }

    public SymbolTableStack getSymTabStack() {
        return symbolTableStack;
    }

    public Token currentToken() {
        return scanner.currentToken();
    }

    public Token nextToken() throws IOException {
        return scanner.nextToken();
    }
}
