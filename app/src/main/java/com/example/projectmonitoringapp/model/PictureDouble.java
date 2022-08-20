package com.example.projectmonitoringapp.model;

public class PictureDouble {
    private String projectName;
    private String dateType;
    private String type;

    public PictureDouble(String projectName, String dateType, String type) {
        this.projectName = projectName;
        this.dateType = dateType;
        this.type = type;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getType() {
        return dateType;
    }

    public void setType(String type) {
        this.dateType = type;
    }

    public String getOption() {
        return type;
    }

    public void setOption(String option) {
        this.type = option;
    }
}
