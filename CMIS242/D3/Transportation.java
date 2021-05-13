public abstract class Transportation {
    private String model;
    private double averageCost;
    private String speed;

    public Transportation(String model, double averageCost) {
        this.model = model;
        this.averageCost = averageCost;
    }

    public String getModel() {
        return model;
    }

    public double getAverageCost() {
        return averageCost;
    }

    public String getSpeed() {
        return speed;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setAverageCost(double averageCost) {
        this.averageCost = averageCost;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void drive() {
        System.out.println(this.getModel() + " is going " + this.getSpeed() + " on the highway!");
    }
}