package backend;

import intermediate.IntermediateCode;
import intermediate.SymbolTableStack;

import java.io.IOException;
import java.util.ArrayList;

public class Backend {
    public void process(ArrayList<IntermediateCode> intermediateCodes,
                        SymbolTableStack symbolTableStack) throws IOException
    {
        for (IntermediateCode iCode : intermediateCodes) {
            printParseTree(iCode);
        }
    }

    public boolean printParseTree(IntermediateCode intermediateCode) {
        if (intermediateCode == null) {
            return false;
        }

        if (intermediateCode.getText() != null) {
            //System.out.println(intermediateCode.getText() + '\t' + intermediateCode.getType());
            System.out.println(intermediateCode.getText());
        }
        else if (intermediateCode.getCar() != null || intermediateCode.getCdr() != null) {
            System.out.print('(');
        }

        printParseTree(intermediateCode.getCar());

        if (!printParseTree(intermediateCode.getCdr())) {
            System.out.print(')');
        }

        return true;
    }
}
