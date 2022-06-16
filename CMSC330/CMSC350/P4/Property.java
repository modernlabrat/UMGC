public class Property implements StateChangeable {
	String propertyAddress;
	int noOfBedrooms, squareFootage, price;
  Status currentStatus; 
	
	public Property(String propertyAddress, int noOfBedrooms, int squareFootage, int price) {
		this.propertyAddress = propertyAddress;
		this.noOfBedrooms = noOfBedrooms;
		this.squareFootage = squareFootage;
		this.price = price;
		
		this.changeState(Status.FOR_SALE);
		
	}
	
	public void changeState(Status newStatus) {
	  switch(newStatus) {
			case FOR_SALE:
				currentStatus = Status.FOR_SALE;
			break; 
			case UNDER_CONTRACT:
				currentStatus = Status.UNDER_CONTRACT;
			break;
			case SOLD:
			  currentStatus = Status.SOLD;
			break;
			default: 
				currentStatus = Status.FOR_SALE;
			break;
		}
	}
	
	@Override
	public String toString() {
		return "\n Property Address: " + propertyAddress + "\n Number of Bedrooms: " + noOfBedrooms + "\n Square Footage: " + squareFootage + " sq.ft. \n Price: " + price + "\nStatus: " + currentStatus;
	}
}