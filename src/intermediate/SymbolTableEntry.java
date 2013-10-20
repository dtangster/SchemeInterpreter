package intermediate;

import java.util.HashMap;

// We are not using the HashMap it extends for this assignment.
public class SymbolTableEntry extends HashMap<Attribute, Object> {
    private String name;
    private SymbolTable symbolTable; // The table that contains this entry

    public SymbolTableEntry(String name, SymbolTable symbolTable) {
        this.name = name;
        this.symbolTable = symbolTable;
    }

    public String getName() {
        return name;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setAttribute(Attribute key, Object value) {
        put(key, value);
    }

    public Object getAttribute(Attribute key) {
        return get(key);
    }
}
