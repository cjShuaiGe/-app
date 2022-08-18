package com.example.projectmonitoringapp.model;

public class ProjectFreeze {
    private String projectId;
    private String pass;
    private String time;

    public ProjectFreeze(String projectId, String pass, String date) {
        this.projectId = projectId;
        this.pass = pass;
        this.time = date;
    }

    public ProjectFreeze() {
    }

    public String getDate() {
        return time;
    }

    public void setDate(String date) {
        this.time = date;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
