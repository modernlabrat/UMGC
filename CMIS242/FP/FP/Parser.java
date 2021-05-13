import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Parser {
    protected class ParserError extends Exception {
        public ParserError(String errorMessage) {
            super(errorMessage);
        }
    }

    private BufferedReader bufferReader;
    private ArrayList<Media> medias;
    private ArrayList<Object[]> mediasObjects;
    private ArrayList<Integer> ids;

    public Parser() throws ParserError {
        medias = new ArrayList<Media>();
        mediasObjects = new ArrayList<Object[]>();
        ids = new ArrayList<Integer>();
        getFile();
    }

    public ArrayList<Media> getMedias() {
        return medias;
    }

    private void getFile() throws ParserError {
        System.out.println("Select a File\n");
        try {
            int option = -1;

            JFileChooser fileChooser = new JFileChooser("."); // JFileChooser
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            fileChooser.setFileFilter(filter); // set text files only filter

            while (option != JFileChooser.APPROVE_OPTION && option != JFileChooser.CANCEL_OPTION)
                option = fileChooser.showOpenDialog(null);

            if (option == JFileChooser.CANCEL_OPTION) {
                System.out.println("Canceled: No File Selected. Goodbye!");
                System.exit(0);
            }

            bufferReader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
            tokenize();
        } catch (IOException ioe) {
            System.out.println("\tFailed. File Not Retrieved.");
        }
    }

    private void tokenize() throws ParserError {
        try {
            String line;
            int openingBracketCount = 0;
            int closingBracketCount = 0;

            ArrayList<String> preTokens = new ArrayList<String>();

            while ((line = bufferReader.readLine()) != null) {
                line = line.trim(); // remove whitespace

                if (line.length() > 0) {
                    String lastCharInLine = line.substring(line.length() - 1);

                    if (!lastCharInLine.equals(":"))
                        throw new ParserError("All lines must end with a colon.");
                    else
                        preTokens.add(line);
                }
            }

            String lastToken = preTokens.get(preTokens.size() - 1);
            String lastTokenLastChars = lastToken.substring(lastToken.length() - 2);

            String firstToken = preTokens.get(0);
            String firstTokenChar = firstToken.substring(0, 1);

            if (!lastTokenLastChars.equals("]:") || !firstTokenChar.equals("["))
                throw new ParserError(
                        "File does not begin with '[' or end with ']:'. Ensure there is a space after the opening bracket and before the closing bracket.");

            if (openingBracketCount != closingBracketCount)
                throw new ParserError(
                        "File does not contain even amount of Media objects. Check opening and closing brackets.");
            else {
                ArrayList<String> mediaString = new ArrayList<String>();
                for (int i = 1; i <= preTokens.size(); i++) {
                    String last = preTokens.get(i - 1);
                    String lastChars = last.substring(last.length() - 2);

                    if (lastChars.equals("]:")) {
                        mediaString.add(preTokens.get(i - 1));
                        Object[] mediaArray = mediaString.toArray();

                        mediasObjects.add(mediaArray);
                        mediaString.clear();
                    } else
                        mediaString.add(preTokens.get(i - 1));
                }

                for (Object[] object : mediasObjects)
                    parseObject(object);
            }
        } catch (IOException e) {
            System.out.println("Failed: Cannot Tokenize.");
            return;
        }
    }
    
    protected void parseObject(Object[] object) throws ParserError {
        String first = String.valueOf(object[0]);
        first = first.replace("[", "").replace(":", "").trim();

        switch (first) {
            case "EBook":
                parseEBook(object);
                break;
            case "MusicCD":
                parseMusicCD(object);
                break;
            case "MovieDVD":
                parseMovieDVD(object);
                break;
            default:
                throw new ParserError(
                        "Media object is not listed. Include EBook, MusicCD, or MovieDVD in the first line.");
        }
    }
    
    protected LinkedHashMap<String, String> initParse(Object[] object) throws ParserError {
        String id = cleanField("ID", String.valueOf(object[1]));
        String year = cleanField("YEAR", String.valueOf(object[2]));
        String copies = cleanField("COPIES", String.valueOf(object[3]));
        String price = cleanField("PRICE", String.valueOf(object[4]));
        String status = cleanField("STATUS", String.valueOf(object[5]));
        String title = cleanField("TITLE", String.valueOf(object[6]));

        LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();

        data.put("ID", id);
        data.put("YEAR", year);
        data.put("COPIES", copies);
        data.put("PRICE", price);
        data.put("STATUS", status);
        data.put("TITLE", title);

        return data;
    }

    protected void parseEBook(Object[] object) throws ParserError {
        if (object.length == 10) {
            LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();

            data = initParse(object);

            String numChapters = cleanField("NUMCHAPTERS", String.valueOf(object[7]));
            String hasChapters = cleanField("HASCHAPTERS", String.valueOf(object[8]));
            String wordCount = cleanField("WORDCOUNT", String.valueOf(object[9]));

            data.put("NUMCHAPTERS", numChapters);
            data.put("HASCHAPTERS", hasChapters);
            data.put("WORDCOUNT", wordCount);

            parseData("EBook", data);
        } else
            throw new ParserError("One of your EBooks is missing a field or contains too many fields.");
    }
    
    protected void parseMovieDVD(Object[] object) throws ParserError {
        if (object.length == 8) {
            LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();

            data = initParse(object);

            String genre = cleanField("GENRE", String.valueOf(object[7]));

            data.put("GENRE", genre);

            parseData("MovieDVD", data);
        } else
            throw new ParserError("One of your MovieDVDs is missing a field or contains too many fields.");
    }

    protected void parseMusicCD(Object[] object) throws ParserError {
        if (object.length == 9) {
            LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();

            data = initParse(object);

            String artist = cleanField("ARTIST", String.valueOf(object[7]));
            String length = cleanField("LENGTH", String.valueOf(object[8]));

            data.put("ARTIST", artist);
            data.put("LENGTH", length);

            parseData("MusicCD", data);
        } else
            throw new ParserError("One of your MovieDVDs is missing a field or contains too many fields.");
    }

    protected String cleanField(String field, String fieldText) throws ParserError {
        if (fieldText.indexOf("=") != -1) {
            fieldText = fieldText.substring(0, fieldText.length() - 1);
            String[] text = fieldText.split("=");
            String identifier = text[0].toUpperCase();
            if (identifier.toUpperCase().equals(field)) {
                fieldText = fieldText.substring(fieldText.indexOf("=") + 1);
                if (fieldText.indexOf("]") != -1)
                    fieldText = fieldText.replace("]", "").trim();
            } else {
                String message = "There is an invalid field in a Media object: " + identifier;
                throw new ParserError(message);
            }
        } else
            throw new ParserError("There is a field in a Media object that does not include the '=' sign.");
        return fieldText;
    }

    protected void parseData(String type, LinkedHashMap<String, String> data) throws ParserError {
        int id = parseTypeInt(data.get("ID"), "ID");
        int year = parseTypeInt(data.get("YEAR"), "YEAR");
        int copies = parseTypeInt(data.get("COPIES"), "COPIES");
        double price = parseTypeDouble(data.get("PRICE"), "PRICE");
        Status status = convertStatus(data.get("STATUS"));
        String title = data.get("TITLE");
        switch (type) {
            case "EBook":
                int numChapters = parseTypeInt(data.get("NUMCHAPTERS"), "default");
                Boolean hasChapters = parseTypeBoolean(data.get("HASCHAPTERS"));
                int wordCount = parseTypeInt(data.get("WORDCOUNT"), "default");

                EBook ebook = new EBook(id, title, year, price, hasChapters, numChapters, wordCount, status, copies);
                medias.add(ebook);
                break;
            case "MovieDVD":
                String genre = data.get("GENRE");
                MovieDVD movieDVD = new MovieDVD(id, title, year, price, genre, status, copies);
                medias.add(movieDVD);
                break;
            case "MusicCD":
                String artist = data.get("ARTIST");
                String length = parseTypeLength(data.get("LENGTH"));

                MusicCD musicCD = new MusicCD(id, title, year, price, artist, length, status, copies);
                medias.add(musicCD);
                break;
            default:
                throw new ParserError("Error Could Not Parse Media Object");
        }
    }

    protected String parseTypeLength(String length) throws ParserError {
        if (length.matches("([0-9]+)+\\:[0-9]+[0-9]+"))
            return length;
        else {
            String message = "There is an invalid value for LENGTH in one of your MusicCD objects: " + length;
            throw new ParserError(message);
        }
    }
    protected boolean parseTypeBoolean(String sBool) throws ParserError {
        sBool = sBool.toLowerCase();
        switch (sBool) {
            case "true":
                return true;
            case "false":
                return false;
            default:
                throw new ParserError(
                        "The HASCHAPTERS field does not contain 'true' or 'false' in one of your Media objects");
        }
    }
    
    protected double parseTypeDouble(String num, String type) throws ParserError {
        try {
            double number = Double.parseDouble(num);
            return number;
        } catch (NumberFormatException e) {
            String message = type + " does not contain the correct valid type (double) in one of your Media objects.";
            throw new ParserError(message);
        }
    }

    protected int parseTypeInt(String num, String type) throws ParserError {
        try {
            int number = Integer.parseInt(num);

            switch (type) {
                case "ID":
                    boolean contains = ids.indexOf(number) >= 0;

                    if (num.length() == 6) {
                        if (contains) {
                            String message = "There is an ID field that contains an ID that is already in Manager: " + num;
                            throw new ParserError(message);
                        } else {
                            ids.add(number);
                            return number;
                        }
                    } else {
                        String message = "There is an ID field that does not have a six-digit ID: " + num;
                        throw new ParserError(message);
                    }
                case "YEAR":
                    if (num.length() == 4)
                        return number;
                    else {
                        String message = "There is a YEAR field that does not have a four-digit year: " + num;
                        throw new ParserError(message);
                    }
                default:
                    return number;
            }
        } catch (NumberFormatException e) {
            String message = type + " does not contain the correct valid type (int) in one of your Media objects: " + num;
            throw new ParserError(message);
        }
    }

    private Status convertStatus(String stringStatus) throws ParserError {
        Status status;

        switch (stringStatus) {
            case "A":
                status = Status.AVAILABLE;
                return status;
            case "O":
                status = Status.OUT_OF_STOCK;
                return status;
            default:
                throw new ParserError("One of your Media objects has an invalid STATUS field. A or O is accepted.");
        }
    }
}
