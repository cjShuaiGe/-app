package com.example.projectmonitoringapp.model;

public class RcAccess {
    private String projectName;
    private String access;

    public RcAccess(String projectName, String access) {
        this.projectName = projectName;
        this.access = access;
    }

    public RcAccess() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
