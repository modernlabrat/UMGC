/*
 * Author: Kyra Samuel
 * File: MediaRentalSystem.java
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MediaRentalSystem {

    BufferedReader reader;
    boolean active;
    List<String> menuOptions;
    Manager manager;
    ArrayList<Media> medias;

    public MediaRentalSystem() throws IOException {
        manager = new Manager();
        reader = new BufferedReader(new InputStreamReader(System.in));
        medias = new ArrayList<Media>();
        menuOptions = new ArrayList<String>() {
            {
                add("1");
                add("2");
                add("3");
                add("9");
            }
        };

        active = true;

        while(active)
            run();
    }

    public void run() throws IOException {
        System.out.println("\n      MENU      \n");
        System.out.println("1: Load Media Objects\n2: Find Media Objects\n3: Rent Media Object\n9: Quit\n");
        System.out.print("Enter a Selection: ");

        String selection = reader.readLine().trim();

        if (menuOptions.contains(selection)) {
            switch (selection) {
                case "1":
                    load();
                    break;
                case "2":
                    findMedia();
                    break;
                case "3":
                    rentMedia();
                    break;
                case "9":
                    System.out.println("Thank you for using the program. Goodbye!");
                    active = false;
                    System.exit(0);
            }
        } else
            System.out.println("Please enter a valid menu option.");
    }

    protected void load() throws IOException {
        if (medias.size() == 0) {
            medias = manager.loadMedia();

            if (medias.size() > 0) {
                manager.setMedias(medias);
                for (Media media : medias)
                    System.out.println(media.toString());
            }
        } else {
            System.out.print("Do you want to (1) Add to Media List or (2) Overwrite Media List: ");
            String response = reader.readLine().trim();

            switch (response) {
                case "1":
                    ArrayList<Media> newMediasA = manager.loadMedia();

                    if (newMediasA.size() > 0) {
                        for (Media media : newMediasA) {
                            try {
                                manager.addMedia(media);
                                System.out.println("Added " + media.toString());
                            } catch (Manager.ManagerError e) {
                                System.out.println("Unable to add Media: " + e.getMessage());
                            }
                        }

                        medias = manager.getMedias();
                    }
                    break;
                case "2":
                    ArrayList<Media> newMediasO = manager.loadMedia();

                    if (newMediasO.size() > 0) {
                        manager.setMedias(newMediasO);
                        medias = manager.getMedias();
                        for (Media media : medias)
                            System.out.println(media.toString());
                    }
                    break;
                default:
                    System.out.println("Please enter (1) or (2).");
                    load();
            }
        }
    }

    protected void findMedia() throws IOException {
        if (medias.size() > 0) {
            System.out.print("Enter Title: ");
            String title = reader.readLine().trim();

            try {
                ArrayList<Media> matches = manager.find(title);
                for (Media media : matches)
                    System.out.println(media.toString());
            } catch (Manager.ManagerError e) {
                System.out.println(e.getMessage());
            }
        } else
            System.out.println("There are no Media Objects in Manager.");
    }

    protected void rentMedia() throws IOException {
        if (medias.size() > 0) {
            LinkedHashMap<Integer, Media> mediaOptions = new LinkedHashMap<Integer, Media>();
            LinkedHashMap<String, Integer> availableOptions = new LinkedHashMap<String, Integer>();

            int optionsCount = 0;
            int outCount = 0;
            int aCount = 0;

            for (Media media : medias) {
                optionsCount++;
                mediaOptions.put(optionsCount, media);
            }

            System.out.println("Available\n");
            for (int i = 1; i <= optionsCount; i++) {
                if (mediaOptions.get(i).getStatus().name().equals("AVAILABLE")) {
                    aCount++;
                    String optionString = String.valueOf(aCount) + ": title=" + mediaOptions.get(i).getTitle()
                            + " type=" + mediaOptions.get(i).getType() + " id=" + mediaOptions.get(i).getId();
                    System.out.println(optionString);

                    availableOptions.put(String.valueOf(aCount), i);
                } else
                    outCount++;
            }
            
            if (aCount == 0) {
                System.out.print("\tAll Media is Out of Stock.\n");
            } else {
                if (outCount > 0) {
                    System.out.println("\nOut of Stock\n");
                    for (int i = outCount; i < optionsCount; i++) {
                        if (mediaOptions.get(i).getStatus().name().equals("OUT_OF_STOCK")) {
                            String optionString = "title=" + mediaOptions.get(i).getTitle() + " type="
                                    + mediaOptions.get(i).getType() + " id=" + mediaOptions.get(i).getId();
                            System.out.println(optionString);
                        }
                    }
                }

                System.out.print("\nSelect an Option: ");
                String choice = reader.readLine().trim();

                if (availableOptions.containsKey(choice)) {
                    System.out.print("Enter Quantity: ");
                    int quantity = getQuantity();
                    mediaOptions.get(availableOptions.get(choice)).setQuantity(quantity);
                    try {
                        Media media = mediaOptions.get(availableOptions.get(choice));
                        double price = manager.rent(media);
                        System.out.println("Purchase Item: title=" + media.getTitle() + " id=" + String.valueOf(media.getId()));
                        System.out.println(
                                "Total=$" + String.format("%.02f", price) + " Thank you for your purchase!");
                    } catch (Manager.ManagerError e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Please choose a valid option.");
                    rentMedia();
                }
            }
        } else
            System.out.println("There are no Media Objects in Manager.");
    
    }

    private int getQuantity() throws IOException {
        String quantity = reader.readLine().trim();
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException npe) {
            System.out.print("Please enter a valid int: ");
            return getQuantity();
        }
    }
}
