package com.example.school.Service;

import com.example.school.Dto.UserRequest;
import com.example.school.Model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    Integer createUser(UserRequest userRequest);

    User getUserById(Integer userId);

    void updateUser(User user);

    void deleteUserById(Integer userId);
}
