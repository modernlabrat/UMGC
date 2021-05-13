import java.util.Random;

public class Discussion4 {
    public Discussion4() {
        Bike fixieBike = new Bike("Golden Cycles Shocker Fixie", 249.00, false);
        Bike guardianBike = new Bike("Guardian 16 in.", 279.00, true);

        double fixieBikeSpeed = calculateSpeed(fixieBike);
        fixieBike.setSpeed(String.format("%.2f", fixieBikeSpeed) + "MPH");
        fixieBike.drive();

        double guardianBikeSpeed = calculateSpeed(guardianBike);
        guardianBike.setSpeed(String.format("%.2f", guardianBikeSpeed) + "MPH");
        guardianBike.drive();

        Train interCityRailTrain = new Train("Inter-City Rail", 40000000, 98);
        double interCityRailTrainSpeed = calculateSpeed(interCityRailTrain);
        interCityRailTrain.setSpeed(String.format("%.2f", interCityRailTrainSpeed) + "MPH");
        interCityRailTrain.drive();

        Train monorailTrain = new Train("Monorail", 150000000, 18);
        double monorailTrainSpeed = calculateSpeed(monorailTrain);
        monorailTrain.setSpeed(String.format("%.2f", monorailTrainSpeed) + "MPH");
        monorailTrain.drive();
    }
    
    protected double calculateSpeed(Bike bike) {
        Random rand = new Random();
        int speed = rand.nextInt(60);

        if (bike.hasTrainingWheels()) {
            int newSpeed = speed - 10;
            if (newSpeed <= 0)
                speed = 5;
            else
                speed = newSpeed;
        }

        return speed;
    }

    protected double calculateSpeed(Train train) {
        Random rand = new Random();
        int speed = (rand.nextInt(65 - 10) + 10);
        int passengers = train.getNumOfPassengers();

        if (passengers < 20)
            return speed;
        else if (passengers >= 20 && passengers < 60) {
            int newSpeed = speed - 5;
            if (newSpeed <= 0)
                speed = 45;
            else
                speed = newSpeed;
        } else if (passengers >= 60 && passengers < 120) {
            int newSpeed = speed - 10;
            if (newSpeed <= 0)
                speed = 40;
            else
                speed = newSpeed;
        } else {
            int newSpeed = speed - 15;
            if (newSpeed <= 0)
                speed = 35;
            else
                speed = newSpeed;
        }

        return speed;
    }
}
