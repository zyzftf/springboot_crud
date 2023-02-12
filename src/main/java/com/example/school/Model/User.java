package com.example.school.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {

    private Integer userId;

    @NotBlank(message = "姓名不能為空白")
    @Size(min = 1, max = 20, message = "姓名長度必須介於1~20之間")
    private String name;

    @NotBlank(message = "電話不能為空白")
    private String Tel;

    @NotBlank(message = "信箱不能為空白")
    private String email;

    @NotNull(message = "性別不能為空")
    private String gender;

}
