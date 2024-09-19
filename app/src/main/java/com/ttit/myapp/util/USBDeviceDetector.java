package com.ttit.myapp.util;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

public class USBDeviceDetector {

    private Context context;
    private UsbManager usbManager;
    public USBDeviceDetector(Context context) {
        this.context = context;
    }

    public boolean isICCardReaderConnected() {
        usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);

        // 获取所有已连接的USB设备
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();

        // 遍历所有设备，检测是否存在IC卡读卡器
        for (UsbDevice device : deviceList.values()) {
            if (isICCardReader(device)) {
                return true;
            }
        }
        return false;

    }


    private boolean isICCardReader(UsbDevice device) {
        // 在这里判断设备是否是IC卡读卡器
        // 比如通过Vendor ID和Product ID来识别设备
        int vendorId = device.getVendorId();
        int productId = device.getProductId();
        Log.i("提示！！！！！！！！！", "vendorId="+vendorId+"productId"+productId);
        // 假设IC卡读卡器的Vendor ID和Product ID为1234和5678
        if (vendorId == 65535 && productId == 53) {
            return true;
        }
        return false;
    }

}
