public class DistanceConverter extends Converter {
    public DistanceConverter() {
        super();
    }

    public DistanceConverter(double input) {
        super(input);
    }

    @Override
    protected double convert() {
        if ((Double.isNaN(this.getInput())))
            return Double.NaN;
        else
            return this.getInput() * 1.609;
    }
}
