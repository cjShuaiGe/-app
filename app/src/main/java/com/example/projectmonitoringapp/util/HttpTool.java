package com.example.projectmonitoringapp.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class HttpTool {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void caretakerLogin(String key, String text, okhttp3.Callback callback) {
        HttpUtil.postHttp("/user/login", key, text, callback);
    }

    public static void getProject(okhttp3.Callback callback) {
        HttpUtil.getHttp("/project/allProject", callback);
    }

    public static void getUser(okhttp3.Callback callback) {
        HttpUtil.getHttp("/user/getAllUser", callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postFreeze(String key, String text, okhttp3.Callback callback) {
        HttpUtil.postToken("/user/freezeUser", key, text, callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postDown(String key, String text, okhttp3.Callback callback){
        HttpUtil.postToken("/user/forceLogout",key,text,callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postProjectApplication(String key, String text, okhttp3.Callback callback){
        HttpUtil.postToken("/project/update",key,text,callback);
    }
}
