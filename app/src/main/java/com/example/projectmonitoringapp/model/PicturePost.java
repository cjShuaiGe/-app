package com.example.projectmonitoringapp.model;

public class PicturePost {
    private String type;
    private String projectName;

    public PicturePost(String option, String projectName) {
        this.type = option;
        this.projectName = projectName;
    }

    public String getOption() {
        return type;
    }

    public void setOption(String option) {
        this.type = option;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
