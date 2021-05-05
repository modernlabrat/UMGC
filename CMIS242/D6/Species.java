/*
 * Author: Kyra Samuel
 * File: Species.java
 * Class: CMIS242
 * Description: an Genus object has three attributes: genusName and numOfSpecies.
 * Note: This class could extend an imaginary AnimalKingdom class to extend the hierarchy
 */

public class Species {
    private String commonName, scientificName, consumerType;

    public Species() {}

    public Species(String commonName, String scientificName, String consumerType) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.consumerType = consumerType;
    }

    public Species(String scientificName, String consumerType) {
        this.scientificName = scientificName;
        this.consumerType = consumerType;
    }

    public String getCommonName() {
        return this.commonName;
    }

    public String getScientificName() {
        return this.scientificName;
    }

    public String getConsumerType() {
        return this.consumerType;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    protected String speciesInfo() {
        return "Common Name => " + this.getCommonName() + "\nScientific Name => " + this.getScientificName()
                + "\nConsumer Type => " + this.getConsumerType();
    }
    
    protected void speak(String message) {
        System.out.println(this.getCommonName() + " sounds like \"" + message + "\"");
    }

}