package com.ttit.myapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ttit.myapp.R;
import com.ttit.myapp.util.USBDeviceDetector;

public class UsbCardAcivity extends BaseActivity {
    private TextView textCard;
    private EditText editCard;
    private String cardNumber;

    private USBDeviceDetector usbDeviceDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usb_card_acivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textCard = findViewById(R.id.text_card);
        editCard = findViewById(R.id.edit_card);
        usbDeviceDetector();

        // 将焦点设置到隐藏的EditText上
        editCard.requestFocus();

        // 可选：禁用软键盘弹出
        editCard.setShowSoftInputOnFocus(false);

        //监听回车事件
        editCard.setOnKeyListener((new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN  ) {
                    // 获取输入的文本
                    String inputText = editCard.getText().toString();
                    // 在这里处理输入的文本
                    cardNumber = editCard.getText().toString().trim();
                    // 清空输入框
                    showToast("输入的文本：" + cardNumber);
                    editCard.setText("");
                    textCard.setText("刷卡成功！");
                    return true; // 消费事件，阻止默认的回车行为
                }
                return false;
            }
        }));

        // 或者使用以下代码在API 21之前隐藏软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editCard.getWindowToken(), 0);
    }

    //检测USB设备
    private void usbDeviceDetector() {
        // 初始化USBDeviceDetector
        usbDeviceDetector = new USBDeviceDetector(this);
        // 检测IC卡读卡器是否连接
        boolean isConnected = usbDeviceDetector.isICCardReaderConnected();

        // 更新显示结果
        if (isConnected) {
            showToast("ID读卡器已连接");
        } else {
            showToast("ID读卡器未连接");
        }
    }
}