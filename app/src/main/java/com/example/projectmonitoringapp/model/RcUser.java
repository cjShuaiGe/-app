package com.example.projectmonitoringapp.model;

public class RcUser {
    private String userName;
    private String registerTime;

    public RcUser() {
    }

    public RcUser(String userName, String registerTime) {
        this.userName = userName;
        this.registerTime = registerTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
}
