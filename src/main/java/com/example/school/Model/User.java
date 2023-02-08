package com.example.school.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    private Integer userId;

    @NotBlank(message = "姓名不能為空白")
    @Size(min = 1, max = 20, message = "姓名長度必須介於1~20之間")
    private String name;

    @NotBlank(message = "電話不能為空白")
    private String Tel;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", Tel='" + Tel + '\'' +
                '}';
    }
}
