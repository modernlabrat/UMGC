public class Train extends Transportation {
    private int numOfPassengers;

    public Train(String model, double averageCost, int numOfPassengers) {
        super(model, averageCost);
        this.numOfPassengers = numOfPassengers;
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setNumOfPassengers(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    @Override
    public void drive() {
        System.out.println(this.getModel() + " Train is going " + this.getSpeed() + " with " + String.valueOf(this.getNumOfPassengers()) + " passengers!");
    }
}
