package frontend;

import intermediate.IntermediateCodeNode;
import intermediate.SymbolTableStack;

import java.io.IOException;

public class Parser {
    protected SymbolTableStack symbolTableStack;
    protected Scanner scanner;
    protected IntermediateCodeNode intermediateCode;

    public Parser(Scanner scanner) {
        symbolTableStack = new SymbolTableStack();
        this.scanner = scanner;
    }

    public void parse() throws IOException {

    }

    public Scanner getScanner() {
        return scanner;
    }

    public IntermediateCodeNode getICode() {
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
