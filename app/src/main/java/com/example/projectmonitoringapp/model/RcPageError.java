package com.example.projectmonitoringapp.model;

public class RcPageError {
    private String page;
    private String error;

    public RcPageError() {
    }

    public RcPageError(String page, String error) {
        this.page = page;
        this.error = error;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
