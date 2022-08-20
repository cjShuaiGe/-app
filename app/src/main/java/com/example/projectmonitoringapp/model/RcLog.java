package com.example.projectmonitoringapp.model;

public class RcLog {
    private String packageName;
    private String visits;
    private String visits_people;
    private String defeatCount;
    private String rate;

    public RcLog() {
    }

    public RcLog(String packageName, String visits, String visits_people, String defeatCount, String rate) {
        this.packageName = packageName;
        this.visits = visits;
        this.visits_people = visits_people;
        this.defeatCount = defeatCount;
        this.rate = rate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }

    public String getVisits_people() {
        return visits_people;
    }

    public void setVisits_people(String visits_people) {
        this.visits_people = visits_people;
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
}
