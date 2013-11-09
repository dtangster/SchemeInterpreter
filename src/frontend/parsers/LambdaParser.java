package frontend.parsers;

import frontend.Parser;
import frontend.Scanner;
import frontend.Token;
import frontend.TokenType;
import intermediate.IntermediateCode;
import intermediate.SymbolTableStack;

import java.io.IOException;

public class LambdaParser extends Parser {
    public LambdaParser(SymbolTableStack symbolTableStack, Scanner scanner) {
        super(symbolTableStack, scanner);
    }

    @Override
    public IntermediateCode parse() throws IOException {
        IntermediateCode rootNode = null;

        try {
            Token token = currentToken();
            rootNode = new IntermediateCode();
            IntermediateCode newNode = new IntermediateCode();
            newNode.setText(token.getText());
            rootNode.setCar(newNode);
            nextToken(); // Consume lambda
            token = nextToken(); // Consume (

            switch (token.getType()) {
                case REGULAR_SYMBOL:
                    newNode = new IntermediateCode();
                    rootNode.setCdr(newNode);
                    newNode.setCar(new IntermediateCode());
                    newNode = newNode.getCar();

                    while (token.getType() == TokenType.REGULAR_SYMBOL) {
                        IntermediateCode temp = new IntermediateCode();
                        temp.setText(token.getText());
                        newNode.setCar(temp);
                        token = nextToken();

                        if (token.getType() == TokenType.REGULAR_SYMBOL) {
                            newNode.setCdr(new IntermediateCode());
                            newNode = newNode.getCdr();
                        }
                    }

                    token = nextToken(); // Consume )
                    rootNode.getCdr().setCdr((new ListParser(symbolTableStack, scanner)).parse());

                    break;
                default:
                    // Do something else if not one of the above
            }
        } catch (IOException ex) { ex.printStackTrace(); }

        return rootNode;
    }
}
