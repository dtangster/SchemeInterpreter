package intermediate;

import java.util.ArrayList;

public class SymbolTableStack extends ArrayList<SymbolTable> {
    private int currentNestingLevel;

    public SymbolTableStack() {
        this.currentNestingLevel = -1;
    }

    public int getCurrentNestingLevel() {
        return currentNestingLevel;
    }

    public SymbolTable getLocalSymTab() {
        if (!isEmpty()) {
            return get(currentNestingLevel);
        }

        return null;
    }

    public SymbolTableEntry enterLocal(String name) {
        if (!isEmpty()) {
            return get(currentNestingLevel).enter(name);
        }

        return null;
    }

    public SymbolTableEntry lookupLocal(String name) {
        if (!isEmpty()) {
            return get(currentNestingLevel).lookup(name);
        }

        return null;
    }

    public SymbolTableEntry lookup(String name) {
        if (!isEmpty()) {
            SymbolTableEntry foundEntry = null;

            for (int i = currentNestingLevel; i >= 0 && foundEntry == null; i--) {
                foundEntry = get(i).lookup(name);
            }

            return foundEntry;
        }

        return null;
    }

    public SymbolTable pop() {
        if (!isEmpty()) {
            currentNestingLevel--;
            return remove(this.size() -1);
        }

        return null;
    }

    public void push(SymbolTable symbolTable) {
        if (symbolTable != null) {
            currentNestingLevel++;
            symbolTable.setNestingLevel(currentNestingLevel);
            add(symbolTable);
        }
    }
}
