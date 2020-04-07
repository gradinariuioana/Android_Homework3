package com.example.homework3.classes;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private int id;
    private String name;
    private String userName;
    private String email;

    public User (){
    }

    public User fromJSON(JSONObject userJson) {
        try {
            id = userJson.getInt("id");
            name = userJson.getString("name");
            userName = userJson.getString("username");
            email = userJson.getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }
}
