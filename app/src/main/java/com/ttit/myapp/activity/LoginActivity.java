package com.ttit.myapp.activity;

import android.os.Bundle;
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
        }
        navigateTo(UsbCardAcivity.class);
    }

    /**
     * 异步post请求
     */
    private void post() {
        //第一步创建OKHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        //第二步创建RequestBody（Form表达）
//        RequestBody body = new FormBody.Builder()
//                .add("mobile", "demoData")
//                .add("password", "demoData")
//                .build();
        Map m = new HashMap();
        m.put("mobile", "demoData");
        m.put("password", "demoData");
        JSONObject jsonObject = new JSONObject(m);
        String jsonStr = jsonObject.toString();
        RequestBody requestBodyJson =
                RequestBody.create(MediaType.parse("application/json;charset=utf-8")
                        , jsonStr);
        //第三步创建Rquest
        Request request = new Request.Builder()
                .url("http://192.168.31.32:8080/renren-fast/app/login")
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
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();

            }
        });
    }
}