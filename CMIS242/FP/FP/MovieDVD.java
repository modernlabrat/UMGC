public class MovieDVD extends Media {
    private String genre;

    public MovieDVD(int id, String title, int year, double price, String genre, Status status, int copies) {
        super(id, title, year, price, status, copies);
        this.genre = genre;
        this.setType("MovieDVD");
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "MovieDVD [id=" + getId() + ", title=" + getTitle() + ", year=" + getYear() + ", genre=" + getGenre() + "]";
    }
   
}
