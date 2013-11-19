package intermediate;

import frontend.*;

public class IntermediateCode {
    //private String text;
    //private TokenType type;
    private Token token;
    private SymbolTableEntry entry;
    private IntermediateCode car;
    private IntermediateCode cdr;
    private SymbolTable symbolTable;

    /*
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }
    */

    public Token getToken()
    {
        return token;
    }

    public void setToken(Token token)
    {
        this.token = token;
    }

    public SymbolTableEntry getEntry() {
        return entry;
    }

    public void setEntry(SymbolTableEntry entry) {
        this.entry = entry;
    }

    public IntermediateCode getCar() {
        return car;
    }

    public IntermediateCode getCdr() {
        return cdr;
    }

    public void setCar(IntermediateCode car) {
        this.car = car;
    }

    public void setCdr(IntermediateCode cdr) {
        this.cdr = cdr;
    }

    public String toString() {
        return '\t' + token.getText() + '\t' + token.getType();
    }

    public void setSymbolTable (SymbolTable symbolTable)
    {
        this.symbolTable = symbolTable;
    }
}
