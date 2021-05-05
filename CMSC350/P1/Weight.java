import java.text.DecimalFormat; 

public class Weight{

	private int pounds;
	private double ounces;
	private final int OUNCES_IN_POUNDS = 16;
	
	public Weight(int pounds, double ounces) {
		
		this.pounds = pounds;
		this.ounces = ounces;
		
		normalize();
	}
	
	public boolean lessThan(Weight weightObject) {
		
		boolean result;
		
		if (toOunces() < weightObject.toOunces()) {
			result = true;
        } else {
			result = false;
        }
    
        return result;
    } 
 
 
 
    public void addTo(Weight weightObject) {

		// 
		ounces = toOunces();
		pounds = 0;

		double ouncesToBeAdded = weightObject.toOunces();

		ounces += ouncesToBeAdded;

		normalize();
	} 
 
 
 
    public void divide(int divisor) {
       
		 double totalOunces = toOunces();

		 ounces = totalOunces / divisor;
		 pounds = 0;

		 normalize();
    } 
 
 
 
    public String toString() { 

		DecimalFormat dotFormatter = new DecimalFormat("###.###");
		String stringOunces = dotFormatter.format(ounces);

        String output = "\n\t" + pounds + " lbs, " + stringOunces + " oz. ";
        return output;
    
    } 
 
 
 
	private double toOunces() {
		double poundsToOunces = ounces + (pounds * OUNCES_IN_POUNDS);
		return poundsToOunces;
   } 
 
 
 
    private void normalize() {
		
	    if (ounces > OUNCES_IN_POUNDS) { 

            double ouncesConverted = ounces / 16;
            int additionalPounds = (int)ouncesConverted;
            int newPounds = additionalPounds + pounds;
			double remainingOunces = ounces % OUNCES_IN_POUNDS;
			ounces = remainingOunces;
			pounds =  newPounds;
			
		}
	}
}	




