package frontend;

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

            //TODO: These cases are not correct. Just trying to read entire file without errors.
            while (token.getType() != TokenType.END_OF_FILE) {
                switch (token.getType()) {
                    case LEFT_PAREN:
                        token = nextToken();
                    case LAMBDA:
                    case DEFINE:
                    case IDENTIFIER:
                        intermediateCode.setCar(parse());
                        intermediateCode.setCdr(parse());
                        break;
                    case RIGHT_PAREN:
                    case QUOTE:
                    case NOT:
                        token = nextToken();
                        break;
                }

                token = currentToken();
            }
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
