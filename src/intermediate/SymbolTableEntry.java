package intermediate;

import java.util.HashMap;

public class SymbolTableEntry {
    private String name;
    private HashMap<Attribute, Object> attributes;
    private SymbolTableEntry car;
    private SymbolTableEntry cdr;

    public SymbolTableEntry() {

    }
}
