package backend;

import frontend.TokenType;
import intermediate.IntermediateCode;
import intermediate.SymbolTable;
import intermediate.SymbolTableEntry;
import intermediate.SymbolTableStack;

import java.io.IOException;
import java.util.ArrayList;

public class Backend {
    public void process(ArrayList<IntermediateCode> intermediateCodes,
                        SymbolTableStack symbolTableStack) throws IOException
    {
        System.out.println("\n----------Printing Parse Tree---------\n");
        for(IntermediateCode iCode : intermediateCodes) {
            printParseTree(iCode);
        }

        System.out.println("\n----------Printing Symbol Table---------\n");
        printSymbolTableStack(symbolTableStack);
    }

    // TODO: Need to fix printing
    public void printParseTree(IntermediateCode intermediateCode) {
        if (intermediateCode == null) {
            return;
        }

        printParseTree(intermediateCode.getCar());
        printParseTree(intermediateCode.getCdr());

        boolean hasText = intermediateCode.getText() != null;
        boolean hasCar = intermediateCode.getCar() != null;
        boolean hasCdr = intermediateCode.getCdr() != null;
        boolean isLeaf = !hasCar && !hasCdr;
        boolean reserved = TokenType.RESERVED_SYMBOLS.containsKey(intermediateCode.getText())
                        || TokenType.RESERVED_WORDS.containsKey(intermediateCode.getText());

        if (reserved) {
            System.out.print("\n(");
        }

        if (hasText) {
            System.out.print(intermediateCode.getText() + " ");

            if (intermediateCode.getType() == TokenType.LAMBDA) {
                System.out.print("(");
            }
            else if (intermediateCode.getType() == TokenType.LET) {
                System.out.print("((");
            }
        }

        if (hasCar && !hasCdr) {
            System.out.print(")");
        }
    }

    public void printSymbolTableStack(SymbolTableStack symbolTableStack) {
        for (SymbolTable table : symbolTableStack) {
            for (SymbolTableEntry entry : table.values()) {
                System.out.println("NESTING LEVEL: " + table.getNestingLevel() + "\t" + entry);
            }
        }
    }
}