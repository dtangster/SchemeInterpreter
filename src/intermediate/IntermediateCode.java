package intermediate;

public class IntermediateCode {
    private String data;
    private IntermediateCode parent;
    private IntermediateCode car;
    private IntermediateCode cdr;

    public IntermediateCode() {}

    public IntermediateCode(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
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
