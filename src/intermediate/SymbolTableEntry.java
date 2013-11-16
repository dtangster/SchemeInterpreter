package intermediate;

import java.util.HashMap;

// We are not using the HashMap it extends for this assignment.
public class SymbolTableEntry extends HashMap<SymbolTableEntryAttribute, Object> {
    private String name;
    private SymbolTable symbolTable; // The table that contains this entry
    private IntermediateCode linkNode;

    public SymbolTableEntry(String name) {
        this.name = name;
        //this.symbolTable = symbolTable;
    }

   /* public SymbolTableEntry(String name, SymbolTable symbolTable) {
        this.name = name;
        this.symbolTable = symbolTable;
    }  */

    public String getName() {
        return name;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setAttribute(SymbolTableEntryAttribute key, Object value) {
        put(key, value);
    }

    public Object getValue(SymbolTableEntryAttribute key) {
        return get(key);
    }

    public String toString() {
        return name;
    }

    public void setIntermediateCode(IntermediateCode node)
    {
        this.linkNode = node;
    }
}
