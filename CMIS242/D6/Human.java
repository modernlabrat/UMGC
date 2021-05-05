public class Human extends Species {
    private String givenName;
    private int age;

    public Human() {
        super("Human", "Homo sapien", "omnivore");
    }

    public Human(String givenName) {
        super("Human", "Homo sapien", "omnivore");

        this.givenName = givenName;
    }

    public Human(String givenName, int age) {
        super("Human", "Homo sapien", "omnivore"); 

        this.givenName = givenName;
        this.age = age;
    }

    public String getGivenName() {
        return this.givenName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public void speak(String message) { // dynamic binding changes from common name to given name
        System.out.println(this.getGivenName() + " says: \"" + message + "\"");
    }

    public void speak(String message, Human human) { // static binding
        // overloaded speak() method, can be used to start a conversation of some sort.
        String currentSpeaker = this.getGivenName();
        System.out.println("\n" + currentSpeaker + " is speaking to " + human.getGivenName());
        System.out.println("\t" + currentSpeaker + " says: \"" + message + "\"");
    }

    @Override
    public String speciesInfo() {
        return "Human Beings, " + this.getScientificName() +"s, are the only non-existent species in the Homo genus.\nHumans are considered " + this.getConsumerType() + "s, but can choose to adapt to a carnivore or herbivore lifestyle.";
    }
}
