package poslovna.informatika.poslovna.validation;

public abstract class Validator {
    protected String result = "";

    public abstract boolean test();

    public String getResults() {
        return result;
    }

    protected void addResult(String result) {
        this.result += result + '\n';
    }
}
