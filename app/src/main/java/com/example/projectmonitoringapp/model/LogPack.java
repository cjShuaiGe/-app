package com.example.projectmonitoringapp.model;

public class LogPack {
    private String packageName;

    public LogPack(String packName) {
        this.packageName = packName;
    }

    public String getPackName() {
        return packageName;
    }

    public void setPackName(String packName) {
        this.packageName = packName;
    }
}
