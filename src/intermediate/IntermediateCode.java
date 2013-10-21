package intermediate;

public class IntermediateCode {
    private IntermediateCodeType type;
    private IntermediateCode parent;
    private IntermediateCode car;
    private IntermediateCode cdr;


    public IntermediateCode() {}

    public IntermediateCode(IntermediateCodeType type) {
        this.type = type;
    }

    public IntermediateCodeType getType() {
        return type;
    }

    public IntermediateCode getParent() {
        return parent;
    }

    public IntermediateCode getCar() {
        return car;
    }

    public IntermediateCode getCdr() {
        return cdr;
    }


    public void setParent(IntermediateCode parent) {
        this.parent = parent;
    }

    public void setCar(IntermediateCode car) {
        this.car = car;
    }

    public void setCdr(IntermediateCode cdr) {
        this.cdr = cdr;
    }
}
