package com.example.touristguide.network;

import com.example.touristguide.components.Stay;

import java.util.List;

public class StayResponse {

    List<Stay> locations;

    public StayResponse(List<Stay> locations) {
        this.locations = locations;
    }

    public List<Stay> getLocations() {
        return locations;
    }

    public void setLocations(List<Stay> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "StayResponse{" +
                "locations=" + locations +
                '}';
    }
}
