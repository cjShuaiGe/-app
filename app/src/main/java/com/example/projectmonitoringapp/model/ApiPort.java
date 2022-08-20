package com.example.projectmonitoringapp.model;

public class ApiPort {
    private String avgResponseTime;
    private String uri;
    private String rate;

    public ApiPort(String avgResponseTime, String uri, String rate) {
        this.avgResponseTime = avgResponseTime;
        this.uri = uri;
        this.rate = rate;
    }

    public String getAvgResponseTime() {
        return avgResponseTime;
    }

    public void setAvgResponseTime(String avgResponseTime) {
        this.avgResponseTime = avgResponseTime;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
