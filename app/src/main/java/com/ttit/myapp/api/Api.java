package com.ttit.myapp.api;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Api {
    private static OkHttpClient client;
    private static String requsetUrl ;
    private static HashMap<Object, Object> mParams ;
    public static Api api = new Api();

    public  Api(){

    }

    public static Api config(String url , HashMap<Object, Object> params){
        client = new OkHttpClient.Builder()
                .build();
        requsetUrl = ApiConfig.BASE_URL+url;
        mParams = params;
        return api;
    }

    public void postRequest(TtitCallback callback){
        JSONObject jsonObject = new JSONObject(mParams);
        String jsonStr = jsonObject.toString();
        MediaType mediaType=MediaType.Companion.parse("application/json;charset=utf-8");
        RequestBody requestBodyJson=RequestBody.Companion.create(jsonStr, mediaType);

        // 第三步创建Rquest
        Request request = new Request.Builder()
                .url(requsetUrl)
                .addHeader("contentType", "application/json;charset=UTF-8")
                .post(requestBodyJson)
                .build();
        //第四步创建call回调对象
        final Call call = client.newCall(request);
        //第五步发起请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("onFailure", e.getMessage());
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                callback.onSuccess(result);
            }
        });
    }

}
