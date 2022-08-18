package com.example.projectmonitoringapp.liveData;

import androidx.lifecycle.MutableLiveData;

public class MyLiveData {
    private static final MutableLiveData<String> searchData =new MutableLiveData<>();

    public  static MutableLiveData<String> getMessageData() {
        return searchData;
    }

    public MyLiveData() {
    }
}
