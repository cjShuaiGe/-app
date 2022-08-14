package com.example.projectmonitoringapp.model;

public class Send {
    private String encryptStr;
    private String encryptKey;

    public Send(String encryptStr, String encryptKey) {
        this.encryptStr = encryptStr;
        this.encryptKey = encryptKey;
    }

    public Send() {
    }

    public String getEncryptStr() {
        return encryptStr;
    }

    public void setEncryptStr(String encryptStr) {
        this.encryptStr = encryptStr;
    }

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }
}
