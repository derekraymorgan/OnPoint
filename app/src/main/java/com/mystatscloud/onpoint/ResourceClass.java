package com.mystatscloud.onpoint;


public class ResourceClass {

    private String link;
    private String details;
    private int imgResource;

    public ResourceClass(String link, String details, int imgResource) {
        this.setLink(link);
        this.setDetails(details);
        this.setImgResource(imgResource);
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
