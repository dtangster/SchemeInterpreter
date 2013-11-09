package intermediate;

import java.util.ArrayList;

public class SymbolTableStack extends ArrayList<SymbolTable> {
    private int currentNestingLevel;

    public SymbolTableStack() {
        this.currentNestingLevel = 0;
    }

    public int getCurrentNestingLevel() {
        return currentNestingLevel;
    }

    public SymbolTable getLocalSymTab() {
        return get(currentNestingLevel);
    }

    public SymbolTableEntry enterLocal(String name) {
        return get(currentNestingLevel).enter(name);
    }

    public SymbolTableEntry lookupLocal(String name) {
        return get(currentNestingLevel).lookup(name);
    }

    public SymbolTableEntry lookup(String name) {
        return lookupLocal(name);
    }
}
