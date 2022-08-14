package com.example.projectmonitoringapp.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.projectmonitoringapp.Crypt2;
import com.example.projectmonitoringapp.model.Send;
import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    private static String token;



    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postHttp(String url,String key,String text, okhttp3.Callback callback){
        try {
        OkHttpClient client=new OkHttpClient();
        MediaType JSON = MediaType.Companion.parse("application/json;charset=utf-8");
//        String key= Crypt2.getRandomString(16);
//        String message=Crypt2.encryptECB(text,key);
//            String encryptKey=Crypt2.encrypt(key,"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDI3hf95L3aMonXCgG926Gt6nwft8RnhM+6UHVieE4N58V0swNvFVU4XRrlNn4o2vU8eZ5z1c8s2AHEl65ck5kiAPjC82nCgWd4j1sdr2Wvz18B+/DT4PLZum4QzwIAviQfafp1qVbC6fYj0BLyDXmeaO5gi3X19U0kIhUPWbzAqQIDAQAB");
//            System.out.println("encryptKey = " + encryptKey);
            String str=toCrypt(text,key);
            str = decode(str);
            System.out.println(str);
            RequestBody body=RequestBody.Companion.create(str,JSON);
            Request request=new Request.Builder()
                    .url("http://39.98.41.126:31100"+url)
                    .post(body)
                    .build();
            client.newCall(request).enqueue(callback);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getHttp(String url, okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url("http://39.98.41.126:31100"+url)
                .addHeader("Authorization",token)
                .build();
        client.newCall(request).enqueue(callback);
    }




    public static void httpToken(okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        RequestBody body = RequestBody.create(JSON, "{\"encryptStr\":\""+"我叼你"+"\","+"\"encryptKey\":\""+"听到没"+"\"}");
        RequestBody body = new FormBody.Builder()
                .add("encryptStr", "我叼你")
                .add("encryptKey", "听到没")
                .build();
        Request request=new Request.Builder()
                .url("http://39.98.41.126:31100/project/allProject")
                .addHeader("Authorization",token)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        HttpUtil.token = token;
    }


    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String toCrypt(String text, String key){
        String encryptStr=Crypt2.encryptECB(text,key);
        String encryptKey= null;
        try {
            encryptKey = Crypt2.encrypt(key,"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDI3hf95L3aMonXCgG926Gt6nwft8RnhM+6UHVieE4N58V0swNvFVU4XRrlNn4o2vU8eZ5z1c8s2AHEl65ck5kiAPjC82nCgWd4j1sdr2Wvz18B+/DT4PLZum4QzwIAviQfafp1qVbC6fYj0BLyDXmeaO5gi3X19U0kIhUPWbzAqQIDAQAB");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str=new Gson().toJson(new Send(encryptStr,encryptKey));
            return str;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postToken(String url, String key, String text, okhttp3.Callback callback){
        try {
            OkHttpClient client=new OkHttpClient();
            MediaType JSON = MediaType.Companion.parse("application/json;charset=utf-8");
//        String key= Crypt2.getRandomString(16);
//        String message=Crypt2.encryptECB(text,key);
//            String encryptKey=Crypt2.encrypt(key,"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDI3hf95L3aMonXCgG926Gt6nwft8RnhM+6UHVieE4N58V0swNvFVU4XRrlNn4o2vU8eZ5z1c8s2AHEl65ck5kiAPjC82nCgWd4j1sdr2Wvz18B+/DT4PLZum4QzwIAviQfafp1qVbC6fYj0BLyDXmeaO5gi3X19U0kIhUPWbzAqQIDAQAB");
//            System.out.println("encryptKey = " + encryptKey);
            String str=toCrypt(text,key);
            str = decode(str);
            System.out.println(str);
            RequestBody body=RequestBody.Companion.create(str,JSON);
            Request request=new Request.Builder()
                    .url("http://39.98.41.126:31100"+url)
                    .post(body)
                    .addHeader("Authorization",token)
                    .build();
            client.newCall(request).enqueue(callback);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
