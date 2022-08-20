package com.example.projectmonitoringapp.model;

public class ApiPicture {
    private String avgResponseTime;
    private float count;
    private String dateStr;
    private float defeatCount;
    private float percent;
    private float rate;

    public ApiPicture(String avgResponseTime, float count, String dateStr, float defeatCount, float percent, float rate) {
        this.avgResponseTime = avgResponseTime;
        this.count = count;
        this.dateStr = dateStr;
        this.defeatCount = defeatCount;
        this.percent = percent;
        this.rate = rate;
    }

    public String getAvgResponseTime() {
        return avgResponseTime;
    }

    public void setAvgResponseTime(String avgResponseTime) {
        this.avgResponseTime = avgResponseTime;
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

    public float getDefeatCount() {
        return defeatCount;
    }

    public void setDefeatCount(float defeatCount) {
        this.defeatCount = defeatCount;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
