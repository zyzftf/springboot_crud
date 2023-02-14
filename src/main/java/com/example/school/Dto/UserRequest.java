package com.example.school.Dto;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "姓名不能為空白")
    @Size(min = 1, max = 20, message = "姓名長度必須介於1~20之間")
    private String name;

    @NotBlank(message = "電話不能為空白")
    private String tel;

    @NotBlank(message = "信箱不能為空白")
    @Email(message = "信箱必須為email格式")
    private String email;

    @NotNull(message = "性別不能為空")
    private String gender;

}
