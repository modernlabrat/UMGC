public class TemperatureConverter extends Converter {
    public TemperatureConverter() {
        super();
    }

    public TemperatureConverter(double input) {
        super(input);
    }

    @Override
    protected double convert() {
        if ((Double.isNaN(this.getInput()))) 
            return Double.NaN;
        else 
            return ((this.getInput() - 32) * 5) / 9;
    }
}
