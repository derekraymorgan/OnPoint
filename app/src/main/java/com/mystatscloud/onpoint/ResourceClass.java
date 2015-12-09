package com.mystatscloud.onpoint;

// The Resource class is used to hold the URL and details of a web resource
public class ResourceClass {

    private String title; // Title of the resource
    private String link;  // Url to the resource
    private String details; // File details of the resource
    private int imgResource; // Image to represent file type

    // Constructor
    public ResourceClass(String title, String link, String details, int imgResource) {
        this.setTitle(title);
        this.setLink(link);
        this.setDetails(details);
        this.setImgResource(imgResource);
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public int getImgResource() {
        return imgResource;
    }
    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}
