package com.example.vinsent_y.loverspace.entity;

import java.io.Serializable;
import java.util.Date;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser implements Serializable {
    private String real_name;
    private boolean isMale;
    private int age;
    private Date birthday;
    private String address;
    private String lover_name;

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLover_name() {
        return lover_name;
    }

    public void setLover_name(String lover_name) {
        this.lover_name = lover_name;
    }
}
