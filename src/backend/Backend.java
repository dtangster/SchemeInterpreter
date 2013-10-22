package backend;

import intermediate.IntermediateCode;
import intermediate.SymbolTableStack;

import java.io.IOException;

public class Backend {
    public void process(IntermediateCode intermediateCode,
                        SymbolTableStack symbolTableStack) throws IOException
    {
        printParseTree(intermediateCode);
    }

    public boolean printParseTree(IntermediateCode intermediateCode) {
        if (intermediateCode == null) {
            return false;
        }

        if (intermediateCode.getText() != null) {
            //System.out.println(intermediateCode.getText() + '\t' + intermediateCode.getType());
            System.out.println(intermediateCode.getText());
        }
        else {
            System.out.print('(');
        }

        printParseTree(intermediateCode.getCar());

        if (!printParseTree(intermediateCode.getCdr())) {
            System.out.print(')');
        }

        return true;
    }
}
