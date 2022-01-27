package com.example.nasa;

// Image class contains information about images that display on MainActivity screen

public class Image {

    // Variables
    private String imageUrl;
    private String title;

    // Constructor
    public Image(String imageUrl, String title){
        this.imageUrl = imageUrl;
        this.title = title;
    }

    // Getter
    public String getImageUrl() {
        return imageUrl;
    }

    // Setter
    public String getTitle() {
        return title;
    }
}
