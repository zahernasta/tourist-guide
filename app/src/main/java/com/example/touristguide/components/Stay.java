package com.example.touristguide.components;

public class Stay {

    private String location;
    private String description;
    private float rating;
    private int picture;

    public Stay(String location, String description, float rating, int picture) {
        this.location = location;
        this.description = description;
        this.rating = rating;
        this.picture = picture;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Stay{" +
                "location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", picture=" + picture +
                '}';
    }
}
