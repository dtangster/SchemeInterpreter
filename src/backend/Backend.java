package backend;

import frontend.TokenType;
import intermediate.IntermediateCode;
import intermediate.SymbolTable;
import intermediate.SymbolTableEntry;
import intermediate.SymbolTableStack;

import java.io.IOException;
import java.util.ArrayList;

public class Backend {
    private ArrayList<SymbolTable> runtimeDisplay;

    public Backend() {
        runtimeDisplay = new ArrayList<SymbolTable>();
    }

    public void process(ArrayList<IntermediateCode> intermediateCodes, SymbolTableStack runtimeStack) throws IOException {
        System.out.println("\n----------Printing Parse Tree---------\n");
        for(IntermediateCode iCode : intermediateCodes) {
            printParseTree(iCode);
            System.out.println("");
        }

        System.out.println("\n----------Printing Symbol Table---------\n");
        printSymbolTableStack(runtimeStack);

        System.out.println("\n----------Executing---------\n");
        for(IntermediateCode iCode : intermediateCodes) {
            execute(iCode, runtimeStack);
        }
    }

    public void execute(IntermediateCode intermediateCode, SymbolTableStack runtimeStack) throws IOException {

    }

    // TODO: Need to fix printing
    public void printParseTree(IntermediateCode intermediateCode) {
        if (intermediateCode == null) {
            return;
        }

        boolean hasText = (intermediateCode.getToken() != null && intermediateCode.getToken().getText() != null);
        boolean hasCar = intermediateCode.getCar() != null;
        boolean hasCdr = intermediateCode.getCdr() != null;

        if (hasCar && intermediateCode.getCar().getToken() != null
                && isReserved(intermediateCode.getCar().getToken().getText())) {
            System.out.print("\n(");
        }
        else if (hasCar && hasCdr && intermediateCode.getCdr().getCdr() == null
                && isLeaf(intermediateCode.getCar()) && !isReserved(intermediateCode.getCar().getToken().getText()))
        {
            System.out.print("(");
        }

        printParseTree(intermediateCode.getCar());
        printParseTree(intermediateCode.getCdr());

        if (hasText) {
            System.out.print(intermediateCode.getToken().getText() + " ");

            if (intermediateCode.getToken().getType() == TokenType.LET) {
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