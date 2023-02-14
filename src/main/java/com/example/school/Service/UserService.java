package com.example.school.Service;

import com.example.school.Dto.UserRequest;
import com.example.school.Model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();  //  查詢用戶資料

    Integer createUser(UserRequest userRequest);  // 創建新用戶

    User getUserById(Integer userId);

    void updateUser(User user);

    void deleteUserById(Integer userId);
}
