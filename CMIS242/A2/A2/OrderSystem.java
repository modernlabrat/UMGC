import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.lang.model.util.ElementScanner6;

/*
 * Kyra Samuel
 * CMIS 242
 * OrderSystem
 * 04/08/2021
 */
 
public class OrderSystem {
    FruitBasket fruitBasket;

    SweetsBasket sweetsBasket;

    boolean active = true;
    boolean hasGift = false;
    boolean isFruit = true;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    List<String> menuOptions = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
            add("9");
            } };
     
    public OrderSystem() throws IOException {
        while (active)
            run();
     }
    
    public void run() throws IOException {
        System.out.println("\n      MENU      \n");
        System.out.println("1: Order a Gift Basket\n2: Change Gift Basket\n3: Display Gift\n9: Exit Program\n");
        System.out.print("Enter a Selection: ");

        String selection = reader.readLine().trim();

        if (menuOptions.contains(selection)) {
            switch (selection) {
            case "1":
                orderGift();
                break;
            case "2":
                changeGift();
                break;
            case "3":
                displayGift();
                break;
            case "9":
                System.out.println("Thank you for using the program. Goodbye!");
                active = false;
                System.exit(0);
            }
        } else
            System.out.println("Please enter a valid menu option.");
    }
    
    public void orderGift() throws IOException {
        System.out.println("Do you want Fruit Basket (1) or Sweets Basket (2): ");

        String response = reader.readLine().trim();

        if (response.equals("1") || response.equals("2")) {
            char size = retrieveSizeInput();

            if (response.equals("1")) {
                isFruit = true;
                completeOrder(size, true);
            }  else {
                isFruit = false;
                completeOrder(size, true);
            }

            hasGift = true;
        } else {
            System.out.println("Please enter 1 or 2.");
            orderGift();
        }
    }

    public void changeGift() throws IOException {
        if (hasGift) {
            if (isFruit) {
                char currentSize = fruitBasket.getSize();

                System.out.printf("Current gift size is: %s. ", currentSize);
                char size = retrieveSizeInput();
                boolean includesCitrus = fruitBasket.includesCitrus();

                System.out.printf("Current basket citrus=%s.", includesCitrus);
                completeOrder(size, false);
            } else {
                char currentSize = sweetsBasket.getSize();

                System.out.printf("Current gift size is: %s. ", currentSize);
                char size = retrieveSizeInput();
                boolean includesNuts = sweetsBasket.includeNuts();

                System.out.printf("Current basket nuts=%s.", includesNuts);
                completeOrder(size, false);
            }
        } else
            System.out.println("No gift has been ordered yet");
    }

    public void displayGift() {
        if (hasGift) {
            if (isFruit)
                fruitBasket.display();
            else
                sweetsBasket.display();
        } else
            System.out.println("No gift has been ordered yet");
    }
    
    public void completeOrder(char size, boolean newOrder) throws IOException {
        Random rand = new Random();

        if (isFruit) {
            System.out.print("Do you want citrus fruits included? true/false: ");
            String response = reader.readLine().trim().toLowerCase();
        
            boolean includeCitrus = retrieveIncluded(response);
            int numOfFruits = retrieveFruitCount(size);

            if (newOrder) {
                String id = "FB" + String.format("%04d", rand.nextInt(10000));
                fruitBasket = new FruitBasket(id, size, numOfFruits, includeCitrus);
            } else {
                fruitBasket.setIncludeCitrus(includeCitrus);
                fruitBasket.setNumOfFruits(numOfFruits);
                fruitBasket.setSize(size);
            }
    
            double price = fruitBasket.calculatePrice();
            fruitBasket.setPrice(price);
        } else {
            System.out.print("Do you want nuts included? true/false: ");
            String response = reader.readLine().trim().toLowerCase();

            boolean includeNuts = retrieveIncluded(response);

            if (newOrder) {
                String id = "SB" + String.format("%04d", rand.nextInt(10000));
                sweetsBasket = new SweetsBasket(id, size, includeNuts);
            } else 
                sweetsBasket.setIncludeNuts(includeNuts);
                sweetsBasket.setSize(size);

            double price = sweetsBasket.calculatePrice();
            sweetsBasket.setPrice(price);
        }
    }
    
    public char retrieveSizeInput() throws IOException {
        List<String> sizeOptions = new ArrayList<String>() {
            {
                add("S");
                add("M");
                add("L");
            }
        };

        System.out.print("What size do you want: S, M, or L: ");
        String size = reader.readLine().trim().toUpperCase();

        if (sizeOptions.contains(size)) {
            return size.charAt(0);
        } else {
            System.out.println("Please enter S, M, or L.\n");
            return retrieveSizeInput();
        }
    }

    public boolean retrieveIncluded(String response) throws IOException {
        response = response.trim().toLowerCase();

        if (response.equals("true"))
            return true;
        else if (response.equals("false"))
            return false;
        else {
            System.out.print("Please enter true or false: ");
            String newResponse = reader.readLine();
            return retrieveIncluded(newResponse);
        }
    }
    
    public int retrieveFruitCount(char size) {
        switch (size) {
            case 'S':
                return 6;
            case 'M':
                return 9;
            case 'L':
                return 15;
            default:
                return 6;
        }
    }

}