package com.ttit.myapp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ttit.myapp.MainActivity;
import com.ttit.myapp.R;
import com.ttit.myapp.api.Api;
import com.ttit.myapp.api.ApiConfig;
import com.ttit.myapp.api.TtitCallback;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class RegisterActivity extends BaseActivity {
    private EditText etAccount;
    private EditText etPwd,etPwd2;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etAccount = findViewById(R.id.register_et_account);
        etPwd = findViewById(R.id.register_et_pwd);
        etPwd2 = findViewById(R.id.register_et_pwd2);
        btnLogin = findViewById(R.id.register_btn);

        btnLogin.setOnClickListener(v ->{
            String account = etAccount.getText().toString().trim();
            String pwd = etPwd.getText().toString().trim();
            String pwd2 = etPwd2.getText().toString().trim();
            register(account, pwd,pwd2);
        });
    }

    private void register(String account, String pwd, String pwd2) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd2)){
            showToast("账号或密码不能为空");
            return;
        }
        if (!pwd.equals(pwd2)) {
            showToast("两次密码不一致");
            return;
        }

        post(account, pwd);
    }

    private void post(String account, String pwd) {
        HashMap<Object, Object> m = new HashMap<>();
        m.put("mobile", account);
        m.put("password", pwd);

        Api.config(ApiConfig.REGISTER, m).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(String result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("1111111111", result );

                        //把result转换成json对象
                        JSONObject object = null;
                        try {
                            object = new JSONObject(result);
                            String code = object.getString("code");
                            String msg = object.getString("msg");
                            if ("200".equals(code)){
                                showToast("注册成功");
                                //暂缓1秒
                                mSleep(() ->navigateTo(LoginActivity.class),1000);

                            }else {
                                showToast(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onFailure(IOException e) {

            }
        });
    }
}