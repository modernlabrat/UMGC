import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Manager {
    private ArrayList<Media> medias;

    public Manager() {
        medias = new ArrayList<Media>();
    }

    public ArrayList<Media> getMedias() {
        return medias;
    }

    private ArrayList<Media> loadMedia() {
        Parser parser = new Parser();
        return parser.getMedias();
    }

    private Media createMedia(String type, LinkedHashMap<String, String> data) {
        int id = Integer.parseInt(data.get("Id"));
        int year = Integer.parseInt(data.get("Year"));
        int copies = Integer.parseInt(data.get("Copies"));

        double price = Integer.parseInt(data.get("Price"));
        
        String stringStatus = data.get("Status");
        Status status = convertStatus(stringStatus);

        String title = data.get("Title");
        Media media;

        switch(type) {
            case "EBook":
                int numChapters = Integer.parseInt(data.get("NumChapters"));
                int wordCount = Integer.parseInt(data.get("WordCount"));

                boolean hasChapters = Boolean.parseBoolean(data.get("hasChapters"));

                media = new EBook(id, title, year, price, hasChapters, numChapters, wordCount, status, copies);
                break;
            case "MovieDVD":
                String genre = data.get("Genre");

                media = new MovieDVD(id, title, year, price, genre, status, copies);
                break;
            case "MusicCD":
                String artist = data.get("Artist");
                String length = data.get("Length");

                media = new MusicCD(id, title, year, price, artist, length, status, copies);
            default: // creates generic Media object
                media = new Media(id, title, year, price, status, copies);
                break;
        }
        return media;
    }

    private void addMedia(Media media) {
        medias.add(media);
    }

    protected Status convertStatus(String stringStatus) {
        Status status;
    
        switch (stringStatus) {
            case "A":
                status = Status.AVAILABLE;
            case "R":
                status = Status.RENTED;
            case "O":
                status = Status.OUT_OF_STOCK;
            default:
                status = Status.OUT_OF_STOCK;
        }

        return status;
    }
}
