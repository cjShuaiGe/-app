package com.example.projectmonitoringapp.model;

import java.util.List;

public class RcEventList {
    private List<RcEvent> list;

    public RcEventList(List<RcEvent> list) {
        this.list = list;
    }

    public List<RcEvent> getList() {
        return list;
    }

    public void setList(List<RcEvent> list) {
        this.list = list;
    }
}
