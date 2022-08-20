package com.example.projectmonitoringapp.model;

public class ProjectApplication {
    private String projectId;
    private String pass;

    public ProjectApplication(String projectId, String pass) {
        this.projectId = projectId;
        this.pass = pass;
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
