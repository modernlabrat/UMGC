import java.util.ArrayList;

public class Discussion5 {
    Discussion5() {
        ArrayList<Species> speciesAL = new ArrayList<Species>();

        System.out.println("Creating Species Objects: ");
        Species homoSpecies = new Species("Human", "Homo sapien", "omnivore");
        Species felisCatus = new Species("Cats", "Felis catus", "carnivore");

        speciesAL.add(homoSpecies);
        speciesAL.add(felisCatus);

        for (Species specie : speciesAL) {
            System.out.println("\n" + specie.speciesInfo());
        }

        System.out.println("\nCreating Human Objects: \n");
        Human human = new Human();

        System.out.printf("About Humans: %s\n\n", human.speciesInfo());
    
        Human janeDoe = new Human("Jane Doe", 22);
        Human johnSmith = new Human("John Smith", 34);

        johnSmith.speak("I am all alone, does anyone want to talk?");

        janeDoe.speak("I have time for a quick conversation", johnSmith);

        String johnsResponse = "That's wonderful! How are you? My name is " + johnSmith.getGivenName();
        johnSmith.speak(johnsResponse, janeDoe);

        System.out.println("\nCreating Tiger Objects: ");
        Tiger malayanTiger = new Tiger("Malayan", 260.864, 263.43);
        Tiger bengalTiger = new Tiger("Bengal", 260.864, 40);

        malayanTiger.run();
        bengalTiger.diet();
    }

    public static void main(String args[]) {
        new Discussion5();
    }
}
