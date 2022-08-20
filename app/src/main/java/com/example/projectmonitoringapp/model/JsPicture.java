package com.example.projectmonitoringapp.model;

public class JsPicture {
    private float count;
    private String dateStr;
    private float percent;

    public JsPicture(float count, String dateStr, float percent) {
        this.count = count;
        this.dateStr = dateStr;
        this.percent = percent;
    }

    public JsPicture() {
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
