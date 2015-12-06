package com.mystatscloud.onpoint;

public class TestimonialClass {

    private String description;
    private String author;
    private String date;
    private int imgResource;

    public TestimonialClass(String description, String author, String date, int imgResource) {
        this.setDescription(description);
        this.setAuthor(author);
        this.setDate(date);
        this.setImgResource(imgResource);
    }

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

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}
