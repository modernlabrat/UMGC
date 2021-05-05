public class Gift {
    private String id;
    private char size;
    private double price;

    public Gift(String id, char size) {
        this.id = id;
        this.size = size;
    }

    public String getId() {
        return this.id;
    }

    public char getSize() {
        return this.size;
    }

    public double getPrice() {
        return this.price;
    }

    public void setSize(char size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    protected double calculatePrice() {
        char size = this.getSize();
        double price = calculateFlatFee(size);

        return price;
    }
    
    protected double calculateFlatFee(char size) {
        switch (size) {
        case 'S':
            return 19.99;
        case 'M':
            return 29.99;
        case 'L':
            return 39.99;
        default:
            return 19.99;
        }
    }

    protected void display() {
        System.out.printf("\nGift [size=%s id=%s price=%s]", this.getSize(), this.getId(), String.format(
                "%,.2f", this.getPrice()));
    }
}
