package com.example.projectmonitoringapp.model;

public class Freeze {
    private String userId;
    private String date;

    public Freeze(String username, String freezeDate) {
        this.userId = username;
        this.date = freezeDate;
    }

    public Freeze() {
    }

    public String getUsername() {
        return userId;
    }

    public void setUsername(String username) {
        this.userId = username;
    }

    public String getFreezeDate() {
        return date;
    }

    public void setFreezeDate(String freezeDate) {
        this.date = freezeDate;
    }
}
