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

    public void parse() throws IOException {
        parse(intermediateCode);
    }

    public void parse(IntermediateCode root) throws IOException {
        try {
            IntermediateCode newNode;
            Token token = nextToken();

            switch (token.getType()) {
                case LEFT_PAREN:
                    newNode = new IntermediateCode();
                    newNode.setParent(root);
                    root.setCar(newNode);
                    parse(newNode);
                    token = currentToken();

                    if (token.getType() == TokenType.RIGHT_PAREN) {
                        newNode = new IntermediateCode();
                        newNode.setParent(root);
                        root.setCdr(newNode);
                        parse(newNode);
                    }

                    break;
                case RIGHT_PAREN:
                    root.getParent().setCdr(null);
                    break;
                case END_OF_FILE:
                    break;
                default:
                    newNode = new IntermediateCode();
                    newNode.setParent(root);
                    root.setText(token.getText());
                    root.setType(token.getType().getText());
                    root.setCdr(newNode);
                    parse(newNode);
                    break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
