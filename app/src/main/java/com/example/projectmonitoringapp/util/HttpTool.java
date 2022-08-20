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
    public static void postRegister(String key, String text, okhttp3.Callback callback) {
        HttpUtil.postHttp("/user/register",key,text,callback);
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

    public static void getLog(okhttp3.Callback callback){
        HttpUtil.getHttp("/apiError/serverPackage",callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postPack(String key, String text, okhttp3.Callback callback){
        HttpUtil.postToken("/apiError/serverMethod",key,text,callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postJsPicture(String key, String text, okhttp3.Callback callback){
        HttpUtil.postToken("/jsError/err",key,text,callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postJs(String key, String text, okhttp3.Callback callback){
        HttpUtil.postToken("/jsError/urlErr",key,text,callback);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postResourceRc(String key, String text, okhttp3.Callback callback){
        HttpUtil.postToken("/resource/brr",key,text,callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postResourceBin(String key, String text, okhttp3.Callback callback){
        HttpUtil.postToken("/resource/count",key,text,callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postMonitorCount(String key, String text, okhttp3.Callback callback){
        HttpUtil.postToken("/SDK/whole",key,text,callback);
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postResourcePicture(String key, String text, okhttp3.Callback callback){
        HttpUtil.postToken("/resource/err",key,text,callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postApiPicture(String key, String text, okhttp3.Callback callback){
        HttpUtil.postToken("/apiError/err",key,text,callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postApiBiao(String key, String text, okhttp3.Callback callback){
        HttpUtil.postToken("/apiError/methodError",key,text,callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postBaiPicture(String key, String text, okhttp3.Callback callback){
        HttpUtil.postToken("/blankError/brr",key,text,callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postPerformancePicture(String key, String text, okhttp3.Callback callback){
        HttpUtil.postToken("/performance",key,text,callback);
    }
}
