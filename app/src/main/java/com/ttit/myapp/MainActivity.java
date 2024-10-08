package com.ttit.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ttit.myapp.activity.BaseActivity;
import com.ttit.myapp.activity.LoginActivity;
import com.ttit.myapp.activity.RegisterActivity;

public class MainActivity extends BaseActivity {

    private Button btnLogin;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin = findViewById(R.id.bt_login);
        btnLogin.setOnClickListener(v -> {
            navigateTo(LoginActivity.class);
        });
        btnRegister = findViewById(R.id.bt_register);
        btnRegister.setOnClickListener(v -> {
            navigateTo(RegisterActivity.class);
        });
    }
}