package frontend.parsers;

import frontend.Parser;
import frontend.Scanner;
import frontend.Token;
import intermediate.IntermediateCode;
import intermediate.SymbolTableEntry;
import intermediate.SymbolTableStack;

import java.io.IOException;

public class ListParser extends Parser {
    public ListParser(SymbolTableStack symbolTableStack, Scanner scanner) {
        super(symbolTableStack, scanner);
    }

    @Override
    public IntermediateCode parse() throws IOException {
        IntermediateCode rootNode = null;

        try {
            Token token = nextToken(); // Consume (
            rootNode = new IntermediateCode();
            IntermediateCode newNode;
            SymbolTableEntry symbol;

            switch (token.getType()) {
                case DEFINE:
                    newNode = new IntermediateCode();
                    newNode.setText(token.getText());
                    rootNode.setCar(newNode);
                    rootNode.setCdr((new DefineParser(symbolTableStack, scanner)).parse());
                    break;
                case LAMBDA:
                    newNode = new IntermediateCode();
                    newNode.setText(token.getText());
                    rootNode.setCar(newNode);
                    rootNode.setCdr((new LambdaParser(symbolTableStack, scanner)).parse());
                    break;
                case LET:
                    newNode = new IntermediateCode();
                    newNode.setText(token.getText());
                    rootNode.setCar(newNode);
                    rootNode.setCdr((new LetParser(symbolTableStack, scanner)).parse());
                    break;
                case REGULAR_SYMBOL:
                    symbol = symbolTableStack.lookup(token.getText());
                    // Do something
                    break;
                case RESERVED_SYMBOL:
                    symbol = symbolTableStack.lookup(token.getText());
                    // Do something
                    break;
                default:
                    // Do something else if not one of the above
            }
        } catch (IOException ex) { ex.printStackTrace(); }

        return rootNode;
    }
}
