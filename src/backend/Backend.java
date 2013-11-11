package backend;

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

        if (intermediateCode.getText() != null) {
            System.out.print("(" + intermediateCode.getText());
        }

        if (intermediateCode.getCdr() == null) {
            //System.out.println(")");
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