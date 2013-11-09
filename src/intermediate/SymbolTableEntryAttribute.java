package intermediate;

public enum SymbolTableEntryAttribute {
    VALUE, // Some constant like 5, 7.4, "Somestring"
    PROCEDURE, // A single intermediateCode that is the root of the tree
    PARAMETERS, // ArrayList<intermediateCode>
}