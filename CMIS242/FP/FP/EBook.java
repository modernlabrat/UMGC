public class EBook extends Media {
    private int numChapters, wordCount;
    private boolean hasChapters;

    public EBook(int id, String title, int year, double price, Boolean hasChapters, int wordCount, Status status, int copies) {
        super(id, title, year, price, status, copies);
        this.hasChapters = hasChapters;
        this.wordCount = wordCount;
        this.setType("EBook");
        numChapters = 0;
    }

    public EBook(int id, String title, int year, double price, Boolean hasChapters, int numChapters, int wordCount,
            Status status, int copies) {
        super(id, title, year, price, status, copies);
        this.hasChapters = hasChapters;
        this.numChapters = numChapters;
        this.wordCount = wordCount;
        this.setType("EBook");
    }

    public int getNumChapters() {
        return numChapters;
    }

    public int getWordCount() {
        return wordCount;
    }

    public Boolean hasChapters() {
        return hasChapters;
    }

    public void setNumChapters(int numChapters) {
        this.numChapters = numChapters;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public void setHasChapters(boolean hasChapters) {
        this.hasChapters = hasChapters;
    }

    @Override
    public double calculateRentalFee() {
        double price = this.getPrice() * this.getQuantity();
        double fee = 0.99;
        return price + fee;
    }

    @Override
    public String toString() {
        return "EBook [id=" + getId() + ", title=" + getTitle() + ", year=" + getYear() + ", chapters=" + getNumChapters()
                + ", wordCount=" + getWordCount() + "]";
    }
}