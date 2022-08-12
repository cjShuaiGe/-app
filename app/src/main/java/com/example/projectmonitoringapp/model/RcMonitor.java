package com.example.projectmonitoringapp.model;

public class RcMonitor {
    private String introduction;
    private String pv;
    private String uv;
    private String render;
    private String js;
    private String api;

    public RcMonitor(String introduction, String pv, String uv, String render, String js, String api) {
        this.introduction = introduction;
        this.pv = pv;
        this.uv = uv;
        this.render = render;
        this.js = js;
        this.api = api;
    }

    public RcMonitor() {
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
