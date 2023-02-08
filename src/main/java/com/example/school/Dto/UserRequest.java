package com.example.school.Dto;


import jakarta.validation.constraints.*;

public class UserRequest {

    @NotBlank(message = "姓名不能為空白")
    @Size(min = 1, max = 20, message = "姓名長度必須介於1~20之間")
    private String name;

    @NotBlank(message = "電話不能為空白")
    private String tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
