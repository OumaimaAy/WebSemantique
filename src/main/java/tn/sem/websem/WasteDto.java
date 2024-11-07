package tn.sem.websem;

import java.time.LocalDateTime;

public class WasteDto {



    private String image;


    private int quantity;


    private LocalDateTime collection_date;


    private String collection_location;

    // Getters and Setters




    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCollection_date() {
        return collection_date;
    }

    public void setCollection_date(LocalDateTime collection_date) {
        this.collection_date = collection_date;
    }

    public String getCollection_location() {
        return collection_location;
    }

    public void setCollection_location(String collection_location) {
        this.collection_location = collection_location;
    }
}
