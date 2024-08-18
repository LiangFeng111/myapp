package com.ttit.myapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ttit.myapp.R;
import com.ttit.myapp.api.Api;
import com.ttit.myapp.api.ApiConfig;
import com.ttit.myapp.api.TtitCallback;
import com.ttit.myapp.util.AppConfig;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {
    private EditText etAccount;
    private EditText etPwd;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etAccount = findViewById(R.id.et_account);
        etPwd = findViewById(R.id.et_pwd);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v ->{
            String account = etAccount.getText().toString().trim();
            String pwd = etPwd.getText().toString().trim();
            login(account, pwd);
        });
    }
    private void login(String account, String pwd){
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd)){
            showToast("账号或密码不能为空");
            return;
        }
        post(account, pwd);

    }

    /**
     * 异步post请求
     */
    private void post(String account, String pwd) {
        HashMap<Object, Object> m = new HashMap<>();
        m.put("mobile", account);
        m.put("password", pwd);

        Api.config(ApiConfig.LOGIN, m).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("1111111111", result );
                //把result转换成json对象
                JSONObject object = null;
                showToastAsync("123");
                try {
                    object = new JSONObject(result);
                    String code = object.getString("code");
                    String msg = object.getString("msg");
                    if ("200".equals(code)){
                        showToast("登录成功");
                        //暂缓1秒
                        mSleep(() ->navigateTo(UsbCardAcivity.class),1000);

                    }else {
                        showToastAsync(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(IOException e) {

            }
        });
    }


}