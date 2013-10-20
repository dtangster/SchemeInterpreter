package intermediate;

public class IntermediateCodeNode {
    private String data;
    private IntermediateCodeNode parent;
    private IntermediateCodeNode car;
    private IntermediateCodeNode cdr;

    public IntermediateCodeNode(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public IntermediateCodeNode getParent() {
        return parent;
    }

    public IntermediateCodeNode getCar() {
        return car;
    }

    public IntermediateCodeNode getCdr() {
        return cdr;
    }

    public void setParent(IntermediateCodeNode parent) {
        this.parent = parent;
    }

    public void setCar(IntermediateCodeNode car) {
        this.car = car;
    }

    public void setCdr(IntermediateCodeNode cdr) {
        this.cdr = cdr;
    }
}
