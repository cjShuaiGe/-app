package com.example.projectmonitoringapp.model;

public class RcAudit {
    private String projectName;
    private String applicationName;
    private String applicationTime;
    private String address;
    private String introduction;

    public RcAudit(String projectName, String applicationName, String applicationTime, String address, String introduction) {
        this.projectName = projectName;
        this.applicationName = applicationName;
        this.applicationTime = applicationTime;
        this.address = address;
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RcAudit() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = applicationTime;
    }
}
