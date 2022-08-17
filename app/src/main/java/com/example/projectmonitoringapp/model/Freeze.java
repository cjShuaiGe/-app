package com.example.projectmonitoringapp.model;

public class Freeze {
    private String username;
    private String freezeDate;

    public Freeze(String username, String freezeDate) {
        this.username = username;
        this.freezeDate = freezeDate;
    }

    public Freeze() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(String freezeDate) {
        this.freezeDate = freezeDate;
    }
}
