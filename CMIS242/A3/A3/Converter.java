public class Converter {
    private double input;

    public Converter() {
        input = Double.NaN;
    }

    public Converter(double input) {
        this.input = input;
    }

    public double getInput() {
        return this.input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    protected double convert() {
        return input;
    }
}