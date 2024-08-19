package com.ttit.myapp.entity;

public class LoginResponse {

    /**
     * code : 0
     * msg : 用户名重复
     * data : 123
     * token : dsadsdasdsdasd
     */

    private int code;
    private String msg;
    private String data;

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
