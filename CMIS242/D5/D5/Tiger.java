
import java.text.DecimalFormat;
import java.util.Random;

public class Tiger extends Species {
    private double weight, tailLength;
    DecimalFormat df = new DecimalFormat("#.##");

    public Tiger(String commonName, double weight, double tailLength) {
        super(commonName, "Panthera tigris", "carnivore");

        this.weight = weight;
        this.tailLength = tailLength;
    }

    public double getTailLength() {
        return tailLength;
    }

    public double getWeight() {
        return weight;
    }

    public void setTailLength(double tailLength) {
        this.tailLength = tailLength;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void run() {
        Random r = new Random();
        double speed = 30 + (40 - 30) * r.nextDouble();

        System.out.printf("\nA %s Tiger just escaped the National Forest and was seen running by witnesses at a roaring %s mph",
                this.getCommonName(), df.format(speed));
    }

    public void diet() {
        Random r = new Random();
        double poundsLost = 1 + r.nextDouble() * 20;

        double weight = this.getWeight() - poundsLost;

        System.out.printf(
                "\n\nThis %s Tiger has been on a healthy diet. It previously weighed %s lbs, after dieting, it now weighs %s lbs",
                this.getCommonName(), df.format(this.getWeight()), df.format(weight));
        this.setWeight(weight);
    }
}

