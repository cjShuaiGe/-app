package com.example.projectmonitoringapp.model;

public class Performance {
     private float consumeTime;
     private String dateStr;

    public float getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(float consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Performance(float consumeTime, String dateStr) {
        this.consumeTime = consumeTime;
        this.dateStr = dateStr;
    }
}
