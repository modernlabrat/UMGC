import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Discussion6 {
    Discussion6() throws IOException {
        ArrayList<Species> speciesAL = new ArrayList<Species>();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Creating Species Objects: ");
        Species homoSpecies = new Species("Human", "Homo sapien", "omnivore");
        Species felisCatus = new Species("Cats", "Felis catus", "carnivore");

        speciesAL.add(homoSpecies);
        speciesAL.add(felisCatus);

        for (Species specie : speciesAL) 
            System.out.println("\n" + specie.speciesInfo()); // note: this is speciesInfo for Species objects
        
        
        Human aHuman = new Human();

        System.out.println("\n" + aHuman.speciesInfo() + "\n"); // dynamic binding, see change in output for Human objects
        felisCatus.speak("meowwww");

        System.out.println("\nStarting virtual conversation: \n");
        System.out.print("What is your first name?: ");
        String firstName = input.readLine().trim();
        
        System.out.print("What is your last name?: ");
        String lastName = input.readLine().trim();

        String fullName = firstName + " " + lastName;

        Human johnSmith = new Human("John Smith", 34);
        Human human = new Human(fullName);

        johnSmith.speak("I am all alone. Would someone like to talk to me?"); // dynamic binding

        String welcome = "Hello, I am " + fullName;

        human.speak(welcome, johnSmith); // static binding

        String johnsResponse = "How are you? My name is " + johnSmith.getGivenName();
        johnSmith.speak(johnsResponse, human); // static binding

        System.out.print("How are you?: ");

        String response = input.readLine().trim();
        human.speak(response, johnSmith); // static binding

        response = response.replace("really", "");
        response = response.replace("so", "");

        String[] notGoodResponses = new String[] { "not okay", "not well", "bad", "horrible", "terrible", "not good",
                "not good", "the worst", "not alright", "not great", "not fantastic", "not wonderful", "awful", "could be better", "not my day"};
        String[] goodResponses = new String[] { "is okay", "not bad", "not horrible", "not terrible", "good",
                "could be worse", "not the worst", "alright", "great", "fantastic", "wonderful", "well"};

        response = response.toLowerCase();
        boolean badResponse = false;
        boolean goodResponse = false;
        boolean johnsResponded = false;

    
        for (String notGoodResponse : notGoodResponses) {
            if (!johnsResponded) {
                if (response.contains(notGoodResponse)) {
                    johnsResponse = "I am sorry to hear that. I hope your day gets better. Here is $1,000,000";
                    
                    johnSmith.speak(johnsResponse, human);
                    
                    badResponse = true;
                    johnsResponded = true;
                }
            }
        }
        
        if (!badResponse) {
            for (String aGoodResponse : goodResponses) {
                if (!johnsResponded) {
                    if (response.contains(aGoodResponse)) {
                        johnsResponse = "That's great to hear. I hope I can make your day better with $1,000,000";
                        
                        johnSmith.speak(johnsResponse, human);
                        
                        goodResponse = true;
                        johnsResponded = true;
                    }
                }
            }
        }

        if (!badResponse && !goodResponse) {
            johnsResponse = "Splendid, here is $1,000,000.";
            johnSmith.speak(johnsResponse, human);
        }

        human.speak("Um wow!? Thank you so much!", johnSmith);

        System.out.println("*John Smith runs into the bushes* " + human.getGivenName() + " hears a faint voice in the background\n");
        johnSmith.speak("I am all alone. Would someone like to talk to me?"); // dynamic binding
    }

    public static void main(String args[]) {
        try {
            new Discussion6();
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
