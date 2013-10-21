package intermediate;

public class IntermediateCode {
    private String text;
    private String type;
    private IntermediateCode car;
    private IntermediateCode cdr;

    public IntermediateCode() {}

    public IntermediateCode(String text, String type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(String type) {
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
}
