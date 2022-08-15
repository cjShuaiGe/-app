package com.example.projectmonitoringapp.model;

public class RcLog {
    private String name;
    private String visitNumber;
    private String visitPeople;
    private String error;
    private String rate;

    public RcLog() {
    }

    public RcLog(String name, String visitNumber, String visitPeople, String error, String rate) {
        this.name = name;
        this.visitNumber = visitNumber;
        this.visitPeople = visitPeople;
        this.error = error;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }

    public String getVisitPeople() {
        return visitPeople;
    }

    public void setVisitPeople(String visitPeople) {
        this.visitPeople = visitPeople;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
