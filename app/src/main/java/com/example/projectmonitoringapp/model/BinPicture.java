package com.example.projectmonitoringapp.model;

public class BinPicture {
    private float count;
    private float percent;
    private String tagname;

    public BinPicture(float count, float percent, String tagname) {
        this.count = count;
        this.percent = percent;
        this.tagname = tagname;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }
}
