package com.example.touristguide.components;

public class Favorite {

    String id;
    String location;
    String description;
    String rating;

    public Favorite() {}

    public Favorite(String id, String location, String description, String rating) {
        this.id = id;
        this.location = location;
        this.description = description;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }


}
