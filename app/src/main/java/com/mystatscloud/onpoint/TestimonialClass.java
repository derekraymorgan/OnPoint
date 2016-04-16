package com.mystatscloud.onpoint;

// The Testimonial class is used to hold the description and details of a testimonial
public class TestimonialClass {

    private String description; // What the testimonial testifies
    private String author; // The author of the testimony
    private String date; // The date of the testimony
    private int imgResource; // An image resource to reflect rating

    // Constructor
    public TestimonialClass(String description, String author, String date, int imgResource) {
        this.setDescription(description);
        this.setAuthor(author);
        this.setDate(date);
        this.setImgResource(imgResource);
    }

    // Getters and Setters
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public int getImgResource() { return imgResource; }
    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}
