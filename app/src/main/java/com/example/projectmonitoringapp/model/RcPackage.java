package com.example.projectmonitoringapp.model;

public class RcPackage {
    private String defeatCount;
    private String rate;
    private String visits;
    private String uri;
    private String visits_people;

    public RcPackage(String defeatCount, String rate, String visits, String uri, String visits_people) {
        this.defeatCount = defeatCount;
        this.rate = rate;
        this.visits = visits;
        this.uri = uri;
        this.visits_people = visits_people;
    }

    public RcPackage() {
    }

    public String getDefeatCount() {
        return defeatCount;
    }

    public void setDefeatCount(String defeatCount) {
        this.defeatCount = defeatCount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getVisits_people() {
        return visits_people;
    }

    public void setVisits_people(String visits_people) {
        this.visits_people = visits_people;
    }
}
