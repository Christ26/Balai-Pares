package com.app.balaipares;

public class UserModel {
    String name,PhoneNumber,key;

    public UserModel(){

    }

    public UserModel(String name, String phoneNumber, String key) {
        this.name = name;
        PhoneNumber = phoneNumber;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
