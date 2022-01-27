package com.example.nasa;

public class Image {
    private String imageUrl;
    private String title;

    public Image(String imageUrl, String title){
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }
}
