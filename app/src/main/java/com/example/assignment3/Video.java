package com.example.assignment3;

public class Video {
    private int id;
    private String textName;
    private String textShortDesc;
    private double rating;
    private int image; //in case where I know how to put a preview image
    private String link;

    public Video(int id, String textName, String textShortDesc, double rating, int image, String link) {
        this.id = id;
        this.textName = textName;
        this.textShortDesc = textShortDesc;
        this.rating = rating;
        this.image = image;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public String getTextName() {
        return textName;
    }

    public String getTextShortDesc() {
        return textShortDesc;
    }

    public double getRating() {
        return rating;
    }

    public int getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }
}
