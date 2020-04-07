package com.example.homework3.classes;
import org.json.JSONException;
import org.json.JSONObject;

public class ToDo {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public ToDo fromJSON(JSONObject todoJson) {
        try {
            userId = todoJson.getInt("userId");
            id = todoJson.getInt("id");
            title = todoJson.getString("title");
            completed = todoJson.getBoolean("completed");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public boolean getCompleted() {
        return completed;
    }
}
