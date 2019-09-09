package com.su.zshop.pojo;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String userName;
    private int age;
    private int fkAddressId;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", fkAddressId=" + fkAddressId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFkAddressId() {
        return fkAddressId;
    }

    public void setFkAddressId(int fkAddressId) {
        this.fkAddressId = fkAddressId;
    }
}
