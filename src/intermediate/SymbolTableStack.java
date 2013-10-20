package intermediate;

import java.util.ArrayList;

public class SymbolTableStack extends ArrayList<SymbolTable> {
    private int currentNestingLevel; // Scope level
}
