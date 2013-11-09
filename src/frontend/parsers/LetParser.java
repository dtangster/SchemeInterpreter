package frontend.parsers;

import frontend.Parser;
import frontend.Scanner;
import frontend.Token;
import frontend.TokenType;
import intermediate.IntermediateCode;
import intermediate.SymbolTableStack;

import java.io.IOException;

public class LetParser extends Parser {
    public LetParser(SymbolTableStack symbolTableStack, Scanner scanner) {
        super(symbolTableStack, scanner);
    }

    @Override
    public IntermediateCode parse() throws IOException {
        IntermediateCode rootNode = null;

        try {
            Token token = nextToken(); // Consume let
            token = nextToken(); // Consume (
            rootNode = new IntermediateCode();
            IntermediateCode newNode;

            switch (token.getType()) {
                case LEFT_PAREN:
                    newNode = new IntermediateCode();
                    newNode.setText(token.getText());
                    rootNode.setCar(newNode);

                    while (token.getType() == TokenType.LEFT_PAREN) {
                        rootNode.setCdr((new ListParser(symbolTableStack, scanner)).parse());
                    }

                    break;
                default:
                    // Do something else if not one of the above
            }
        } catch (IOException ex) { ex.printStackTrace(); }

        return rootNode;
    }
}
