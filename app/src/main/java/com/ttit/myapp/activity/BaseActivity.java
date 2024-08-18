package com.ttit.myapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    public Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }
    public void showToast(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    public void showToastAsync(String msg){
        Looper.prepare();
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    public void navigateTo(Class<?> cls){
        Intent intent = new Intent(context,cls);
        startActivity(intent);
    }

    public void mSleep(Runnable action, long delayMillis) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(action, delayMillis);
    }
}
