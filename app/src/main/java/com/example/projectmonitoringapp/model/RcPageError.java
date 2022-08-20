package com.example.projectmonitoringapp.model;

public class RcPageError {
    private String url;
    private String count;

    public RcPageError(String url, String count) {
        this.url = url;
        this.count = count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
