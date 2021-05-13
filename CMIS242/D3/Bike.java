public class Bike extends Transportation {
    private boolean hasTrainingWheels;

    public Bike(String model, double averageCost, Boolean hasTrainingWheels) {
        super(model, averageCost);
        this.hasTrainingWheels = hasTrainingWheels;
    }

    public Boolean hasTrainingWheels() {
        return hasTrainingWheels;
    }

    public void setHasTrainingWheels(boolean hasTrainingWheels) {
        this.hasTrainingWheels = hasTrainingWheels;
    }

    @Override
    public void drive() {
        System.out.println("Current " + this.getModel() + " Bike is going " + this.getSpeed() + " around the neighborhood!");
    }
}
