package com.example.myapplication5.model;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class UserDetails extends JsonClass {

    private int id;
    private String name;
    private String phoneNumber;

    public UserDetails() {}

    public UserDetails(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void initFrom(JSONObject object) throws JSONException {
        if (object == null) return;
        setId(object.getInt("id"));
        setName(object.getString("name"));
        setPhoneNumber(object.getString("phoneNumber"));
    }

    @NonNull
    @Override
    public String toString() {
        String name = getName() != null && !getName().isEmpty() ? getName() : "";
        String phoneNo = getPhoneNumber() != null && !getPhoneNumber().isEmpty() ? "(M: " + getPhoneNumber() + ")" : "";
        return "usr: " + name  + phoneNo;
    }
}
