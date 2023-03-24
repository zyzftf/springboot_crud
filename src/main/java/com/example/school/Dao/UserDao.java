package com.example.school.Dao;

import com.example.school.Dto.UserQueryParams;
import com.example.school.Dto.UserRequest;
import com.example.school.Model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers(UserQueryParams userQueryParams);

    Integer countUser(String search);    //  用戶總數

    Integer createUser(UserRequest userRequest);

    User getUserById(Integer userId);

    void updateUser(User user);

    void deleteUserById(Integer userId);
}
