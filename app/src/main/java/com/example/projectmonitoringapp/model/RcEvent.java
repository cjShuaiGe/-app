package com.example.projectmonitoringapp.model;

public class RcEvent {
    private String name;
    private String s1;
    private String s2;

    public RcEvent(String name, String s1, String s2) {
        this.name = name;
        this.s1 = s1;
        this.s2 = s2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }
}
