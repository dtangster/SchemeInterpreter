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
        for (IntermediateCode iCode : intermediateCodes) {
            printParseTree(iCode);
        }

        System.out.println("\n----------Printing Symbol Table---------\n");
        printSymbolTableStack(symbolTableStack);
    }

    public boolean printParseTree(IntermediateCode intermediateCode) {
        if (intermediateCode == null) {
            return false;
        }

        if (intermediateCode.getText() != null) {
            if (intermediateCode.getText().compareTo("'") == 0) {
                System.out.print(intermediateCode.getText());
            }
            else {
                System.out.print(intermediateCode.getText() + ' ');
            }
        }
        else if (intermediateCode.getCar() != null) {
            System.out.print('(');
        }

        printParseTree(intermediateCode.getCar());

        if (!printParseTree(intermediateCode.getCdr())) {
            System.out.println(')');
        }

        return true;
    }

    public void printSymbolTableStack(SymbolTableStack symbolTableStack) {
        for (SymbolTable table : symbolTableStack) {
            for (SymbolTableEntry entry : table.values()) {
                System.out.println(entry);
            }
        }
    }
}
