package com.example.projectmonitoringapp.model;

public class PictureNewPost {
    private String projectName;
    private String dateType;

    public PictureNewPost(String projectName, String dateType) {
        this.projectName = projectName;
        this.dateType = dateType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }
}
