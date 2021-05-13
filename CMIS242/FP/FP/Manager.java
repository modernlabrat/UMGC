import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Manager {
    protected class ManagerError extends Exception {
        public ManagerError(String errorMessage) {
            super(errorMessage);
        }
    }

    private ArrayList<Media> medias;

    public Manager() {
        medias = new ArrayList<Media>();
    }

    public ArrayList<Media> getMedias() {
        return medias;
    }

    public void setMedias(ArrayList<Media> medias) {
        this.medias = medias;
    }

    protected ArrayList<Media> loadMedia() {
        try {
            Parser parser = new Parser();
            return parser.getMedias();
        } catch (Parser.ParserError e) {
            System.out.println(e.getMessage());
            ArrayList<Media> emptyMedia = new ArrayList<Media>();
            return emptyMedia;
        }
    }

    protected Media createMedia(String type, LinkedHashMap<String, String> data) throws ManagerError {
        int id = Integer.parseInt(data.get("Id"));
        int year = Integer.parseInt(data.get("Year"));
        int copies = Integer.parseInt(data.get("Copies"));

        double price = Integer.parseInt(data.get("Price"));

        String stringStatus = data.get("Status");
        Status status = convertStatus(stringStatus);

        String title = data.get("Title");
        Media media;

        switch (type) {
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

    protected void updateMedia(Media media, LinkedHashMap<String, String> data) throws ManagerError {
        boolean found = false;

        for (Media currentMedia : medias) {
            if (media.getId() == currentMedia.getId())
                found = true;
        }

        if (found) {
            int year = Integer.parseInt(data.get("Year"));
            int copies = Integer.parseInt(data.get("Copies"));

            double price = Integer.parseInt(data.get("Price"));

            String stringStatus = data.get("Status");
            Status status = convertStatus(stringStatus);

            String title = data.get("Title");

            media.setYear(year);
            media.setCopies(copies);
            media.setPrice(price);
            media.setStatus(status);
            media.setTitle(title);
        } else
            throw new ManagerError("This Media ID is not found in Manager.");
    }
    
    protected void updateMedia(EBook ebook, LinkedHashMap<String, String> data) throws ManagerError {
        boolean found = false;

        for (Media currentMedia : medias) {
            if (ebook.getId() == currentMedia.getId())
                found = true;
        }

        if (found) {
            int year = Integer.parseInt(data.get("Year"));
            int copies = Integer.parseInt(data.get("Copies"));

            double price = Integer.parseInt(data.get("Price"));

            String stringStatus = data.get("Status");
            Status status = convertStatus(stringStatus);

            String title = data.get("Title");

            int numChapters = Integer.parseInt(data.get("NumChapters"));
            int wordCount = Integer.parseInt(data.get("WordCount"));

            boolean hasChapters = Boolean.parseBoolean(data.get("hasChapters"));

            ebook.setYear(year);
            ebook.setCopies(copies);
            ebook.setPrice(price);
            ebook.setStatus(status);
            ebook.setTitle(title);
            ebook.setNumChapters(numChapters);
            ebook.setWordCount(wordCount);
            ebook.setHasChapters(hasChapters);
        } else
            throw new ManagerError("This Ebook ID is not found in Manager.");
    }
    
    protected void updateMedia(MovieDVD movieDVD, LinkedHashMap<String, String> data) throws ManagerError {
        boolean found = false;

        for (Media currentMedia : medias) {
            if (movieDVD.getId() == currentMedia.getId())
                found = true;
        }

        if (found) {
            int year = Integer.parseInt(data.get("Year"));
            int copies = Integer.parseInt(data.get("Copies"));

            double price = Integer.parseInt(data.get("Price"));

            String stringStatus = data.get("Status");
            Status status = convertStatus(stringStatus);

            String title = data.get("Title");

            String genre = data.get("Genre");

            movieDVD.setYear(year);
            movieDVD.setCopies(copies);
            movieDVD.setPrice(price);
            movieDVD.setStatus(status);
            movieDVD.setTitle(title);
            movieDVD.setGenre(genre);
        } else
            throw new ManagerError("This Ebook ID is not found in Manager.");
    }

    protected void updateMedia(MusicCD musicCD, LinkedHashMap<String, String> data) throws ManagerError {
        boolean found = false;

        for (Media currentMedia : medias) {
            if (musicCD.getId() == currentMedia.getId())
                found = true;
        }

        if (found) {
            int year = Integer.parseInt(data.get("Year"));
            int copies = Integer.parseInt(data.get("Copies"));

            double price = Integer.parseInt(data.get("Price"));

            String stringStatus = data.get("Status");
            Status status = convertStatus(stringStatus);

            String title = data.get("Title");

            String artist = data.get("Artist");
            String length = data.get("Length");

            musicCD.setYear(year);
            musicCD.setCopies(copies);
            musicCD.setPrice(price);
            musicCD.setStatus(status);
            musicCD.setTitle(title);
            musicCD.setArtist(artist);
            musicCD.setLength(length);
        } else
            throw new ManagerError("This Ebook ID is not found in Manager.");
    }

    protected void addMedia(Media media) throws ManagerError {
        boolean unique = true;
        for (Media currentMedia : medias) {
            if (media.getId() == currentMedia.getId()) {
                unique = false;
                throw new ManagerError("The ID for this Media Object is already in Manager.");
            }
        }

        if (unique)
            medias.add(media);
    }

    protected ArrayList<Media> find(String title) throws ManagerError {
        if (medias.size() > 0) {
            boolean matched = false;
            ArrayList<Media> matches = new ArrayList<Media>();

            for (Media media : medias) {
                if (media.getTitle().equals(title)) {
                    matches.add(media);
                    matched = true;
                }
            }

            if (matched)
                return matches;
            else {
                String message = "There are no Media Objects in the Manager with the title: " + title;
                throw new ManagerError(message);
            }
        } else
            throw new ManagerError("The Manager has no Media Objects stored.");
    }

    protected double rent(Media media) throws ManagerError {
        boolean found = false;

        for (Media currentMedia : medias) {
            if (media.getId() == currentMedia.getId())
                found = true;
        }

        if (found) {
            Status status = media.getStatus();

            if (status.name().equals("OUT_OF_STOCK"))
                throw new ManagerError("This Media Object is out of stock.");
            else {
                int copies = media.getCopies();
                int newCopies = copies - media.getQuantity();

                if (newCopies == 0) {
                    media.setStatus(Status.OUT_OF_STOCK);
                    media.setCopies(newCopies);
                } else if (newCopies < 0) {
                    String message = "There are not enough copies for this purchase. copies=" + String.valueOf(copies);
                    throw new ManagerError(message);
                } else
                    media.setCopies(newCopies);
                
                return media.calculateRentalFee();
            }
        } else
            throw new ManagerError("This Media Object ID is not in Manager");
    }

    private Status convertStatus(String stringStatus) throws ManagerError {
        switch (stringStatus) {
            case "A":
                return Status.AVAILABLE;
            case "O":
                return Status.OUT_OF_STOCK;
            default:
                throw new ManagerError("The Status for this Media is Incorrect.");
        }
    }
}
