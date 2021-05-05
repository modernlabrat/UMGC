public class FruitBasket extends Gift {
    private int numOfFruits;
    private boolean includeCitrus;

    public FruitBasket(String id, char size, int numOfFruits, Boolean includeCitrus) {
        super(id, size);

        this.numOfFruits = numOfFruits;
        this.includeCitrus = includeCitrus;
    }

    public int getNumOfFruits() {
        return numOfFruits;
    }

    public boolean includesCitrus() {
        return this.includeCitrus;
    }

    public void setIncludeCitrus(Boolean includeCitrus) {
        this.includeCitrus = includeCitrus;
    }

    public void setNumOfFruits(int numOfFruits) {
        this.numOfFruits = numOfFruits;
    }

    @Override
    protected double calculatePrice() {
        char size = this.getSize();
        boolean containsCitrus = this.includesCitrus();
        double price = calculateFlatFee(size);

        if (containsCitrus)
            price = price + 5.99;

        return price;
    }

    @Override
    protected void display() {
        System.out.printf("\nFruitBasket [numFruits=%s haveCitrus=%s size=%s id=%s price=%s]", this.getNumOfFruits(), this.includesCitrus(), this.getSize(), this.getId(), 
                String.format("%,.2f", this.getPrice()));
    }
}