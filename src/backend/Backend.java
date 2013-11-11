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

        boolean hasText = intermediateCode.getText() != null;
        boolean hasCar = intermediateCode.getCar() != null;
        boolean hasCdr = intermediateCode.getCdr() != null;

        if (hasCar && isReserved(intermediateCode.getCar().getText())) {
            System.out.print("\n(");
        }
        else if (hasCar && hasCdr && intermediateCode.getCdr().getCdr() == null
                && isLeaf(intermediateCode.getCar()) && !isReserved(intermediateCode.getCar().getText()))
        {
            System.out.print("(");
        }

        printParseTree(intermediateCode.getCar());
        printParseTree(intermediateCode.getCdr());

        if (hasText) {
            System.out.print(intermediateCode.getText() + " ");

            if (intermediateCode.getType() == TokenType.LET) {
                System.out.print("(");
            }
        }

        if (hasCar && !hasCdr) {
            System.out.print(")");
        }
    }

    private boolean isReserved(String text) {
        return TokenType.RESERVED_SYMBOLS.containsKey(text) || TokenType.RESERVED_WORDS.containsKey(text);
    }

    private boolean isLeaf(IntermediateCode node) {
        return node.getCar() == null && node.getCdr() == null;
    }

    public void printSymbolTableStack(SymbolTableStack symbolTableStack) {
        for (SymbolTable table : symbolTableStack) {
            for (SymbolTableEntry entry : table.values()) {
                System.out.println("NESTING LEVEL: " + table.getNestingLevel() + "\t" + entry);
            }
        }
    }
}