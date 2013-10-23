package intermediate;

import frontend.TokenType;

public class IntermediateCode {
    private String text;
    private TokenType type;
    private IntermediateCode car;
    private IntermediateCode cdr;

    public String getText() {
        return text;
    }

    public TokenType getType() {
        return type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(TokenType type) {
        this.type = type;
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
        return '\t' + text + '\t' + type;
    }
}
