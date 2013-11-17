package intermediate;

import frontend.TokenType;

import java.util.ArrayList;

public class SymbolTableStack extends ArrayList<SymbolTable> {
    private int currentNestingLevel;

    public SymbolTableStack() {
        currentNestingLevel = 0;
        SymbolTable global = new SymbolTable();
        add(global);
        push();

        for (String name : TokenType.RESERVED_SYMBOLS.keySet()) {
            global.enter(name);
        }

        for (String name : TokenType.RESERVED_WORDS.keySet()) {
            global.enter(name);
        }
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
        SymbolTableEntry foundEntry = null;

        for (int i = currentNestingLevel; i >= 0 && foundEntry == null; i--) {
            foundEntry = get(i).lookup(name);
        }

        return foundEntry;
    }

    public SymbolTable pop() {
        if (!isEmpty()) {
            return remove(currentNestingLevel--);
        }

        return null;
    }

    public void push() {
        add(new SymbolTable(++currentNestingLevel));
    }

}
