/*
 * Author: Kyra Samuel
 * File: Media.java
 */

public class Media {
    private int id, quantity, year, copies;
    private String title;
    private Status status;
    private double price;
    private String type;

    public Media(int id, String title, int year, double price, Status status, int copies) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.price = price;
        this.status = status;
        this.copies = copies;
        this.type = "Media";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Status getStatus() {
        return status;
    }

    public int getCopies() {
        return copies;
    }

    public String getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double calculateRentalFee() {
        double price = this.getPrice() * this.getQuantity();
        double fee = 2.99;

        return price + fee;
    }
}