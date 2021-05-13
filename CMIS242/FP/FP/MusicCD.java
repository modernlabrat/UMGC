public class MusicCD extends Media {
    private String artist;
    private String length;

    public MusicCD(int id, String title, int year, double price, String artist, String length, Status status, int copies) {
        super(id, title, year, price, status, copies);
        this.artist = artist;
        this.length = length;
        this.setType("MusicCD");
    }

    public String getArtist() {
        return artist;
    }

    public String getLength() {
        return length;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public double calculateRentalFee() {
        double price = this.getPrice() * this.getQuantity();
        double fee = 3.50;
        return price + fee;
    }

    @Override
    public String toString() {
        return "MusicCD [id=" + getId() + ", title=" + getTitle() + ", year=" + getYear() + ", artist=" + getArtist()
                + ", length=" + length + "]";
    }
}
