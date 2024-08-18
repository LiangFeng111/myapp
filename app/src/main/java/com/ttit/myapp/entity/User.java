
package com.ttit.myapp.entity;


import java.util.Date;

public class User {

    private String mobile;

    private String password;
    private Integer age;



    private String iphone;

    private Date cationDate;

    public User() {
    }

    public User(String mobile, String password, Integer age, String iphone, Date cationDate) {
        this.mobile = mobile;
        this.password = password;
        this.age = age;
        this.iphone = iphone;
        this.cationDate = cationDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public Date getCationDate() {
        return cationDate;
    }

    public void setCationDate(Date cationDate) {
        this.cationDate = cationDate;
    }
}
