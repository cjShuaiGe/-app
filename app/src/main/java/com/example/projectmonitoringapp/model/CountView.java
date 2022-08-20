package com.example.projectmonitoringapp.model;

public class CountView {
    private String jsCountIncreRate;
    private String jsCountIncre;
    private String jsThisWeekCount;
    private String JsThisWeekDefeatRate;
    private String ApiThisWeekDefeatRate;
    private String apiThisWeekCount;
    private String apiCountIncreRate;
    private String apiCountIncre;
    private String resourceCountIncreRate;
    private String resourceCountIncre;
    private String resourceThisWeekCount;

    public CountView(String jsCountIncreRate, String jsCountIncre, String jsThisWeekCount, String jsThisWeekDefeatRate, String apiThisWeekDefeatRate, String apiThisWeekCount, String apiCountIncreRate, String apiCountIncre, String resourceCountIncreRate, String resourceCountIncre, String resourceThisWeekCount) {
        this.jsCountIncreRate = jsCountIncreRate;
        this.jsCountIncre = jsCountIncre;
        this.jsThisWeekCount = jsThisWeekCount;
        JsThisWeekDefeatRate = jsThisWeekDefeatRate;
        ApiThisWeekDefeatRate = apiThisWeekDefeatRate;
        this.apiThisWeekCount = apiThisWeekCount;
        this.apiCountIncreRate = apiCountIncreRate;
        this.apiCountIncre = apiCountIncre;
        this.resourceCountIncreRate = resourceCountIncreRate;
        this.resourceCountIncre = resourceCountIncre;
        this.resourceThisWeekCount = resourceThisWeekCount;
    }

    public String getJsCountIncreRate() {
        return jsCountIncreRate;
    }

    public void setJsCountIncreRate(String jsCountIncreRate) {
        this.jsCountIncreRate = jsCountIncreRate;
    }

    public String getJsCountIncre() {
        return jsCountIncre;
    }

    public void setJsCountIncre(String jsCountIncre) {
        this.jsCountIncre = jsCountIncre;
    }

    public String getJsThisWeekCount() {
        return jsThisWeekCount;
    }

    public void setJsThisWeekCount(String jsThisWeekCount) {
        this.jsThisWeekCount = jsThisWeekCount;
    }

    public String getJsThisWeekDefeatRate() {
        return JsThisWeekDefeatRate;
    }

    public void setJsThisWeekDefeatRate(String jsThisWeekDefeatRate) {
        JsThisWeekDefeatRate = jsThisWeekDefeatRate;
    }

    public String getApiThisWeekDefeatRate() {
        return ApiThisWeekDefeatRate;
    }

    public void setApiThisWeekDefeatRate(String apiThisWeekDefeatRate) {
        ApiThisWeekDefeatRate = apiThisWeekDefeatRate;
    }

    public String getApiThisWeekCount() {
        return apiThisWeekCount;
    }

    public void setApiThisWeekCount(String apiThisWeekCount) {
        this.apiThisWeekCount = apiThisWeekCount;
    }

    public String getApiCountIncreRate() {
        return apiCountIncreRate;
    }

    public void setApiCountIncreRate(String apiCountIncreRate) {
        this.apiCountIncreRate = apiCountIncreRate;
    }

    public String getApiCountIncre() {
        return apiCountIncre;
    }

    public void setApiCountIncre(String apiCountIncre) {
        this.apiCountIncre = apiCountIncre;
    }

    public String getResourceCountIncreRate() {
        return resourceCountIncreRate;
    }

    public void setResourceCountIncreRate(String resourceCountIncreRate) {
        this.resourceCountIncreRate = resourceCountIncreRate;
    }

    public String getResourceCountIncre() {
        return resourceCountIncre;
    }

    public void setResourceCountIncre(String resourceCountIncre) {
        this.resourceCountIncre = resourceCountIncre;
    }

    public String getResourceThisWeekCount() {
        return resourceThisWeekCount;
    }

    public void setResourceThisWeekCount(String resourceThisWeekCount) {
        this.resourceThisWeekCount = resourceThisWeekCount;
    }
}
