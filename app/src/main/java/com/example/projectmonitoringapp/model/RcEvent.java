package com.example.projectmonitoringapp.model;

public class RcEvent {
    private String filename;
    private String tagname;

    public RcEvent() {
    }

    public RcEvent(String filename, String tagname) {
        this.filename = filename;
        this.tagname = tagname;
    }

    public String getName() {
        return filename;
    }

    public void setName(String name) {
        this.filename = name;
    }

    public String getS1() {
        return tagname;
    }

    public void setS1(String s1) {
        this.tagname = s1;
    }


}
