package com.example.touristguide.network;

import com.example.touristguide.components.Stay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    public static StayResponse parseJSON(String json) {
        if(json == null) {return null; }
        try {
            JSONObject jsonObject = new JSONObject(json);
            List<Stay> stay = getListFromJSON(jsonObject.getJSONArray("locations"));
            return new StayResponse(stay);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Stay> getListFromJSON(JSONArray jsonArray) throws JSONException {
        if(jsonArray == null) { return null; }
        List<Stay> result = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++) {
            Stay stay = getLocationFromJSON(jsonArray.getJSONObject(i));
            if(stay != null) {
                result.add(stay);
            }
        }

        return result;
    }

    public static Stay getLocationFromJSON(JSONObject jsonObject) throws JSONException{
        int picture = 1;
        if(jsonObject == null ) { return null; }
        String location = jsonObject.getString("location");
        String description = jsonObject.getString("description");
        float rating = (float) jsonObject.getDouble("rating");
        return new Stay(location, description, rating, picture);
    }
}
