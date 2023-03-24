package com.example.school.Service;

import com.example.school.Dao.UserDao;
import com.example.school.Dto.UserQueryParams;
import com.example.school.Dto.UserRequest;
import com.example.school.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUsers(UserQueryParams userQueryParams) { // size = 10, page=1

        return userDao.getUsers(userQueryParams);
    }

    @Override
    public Integer countUser(String search) {
        return userDao.countUser(search);
    }

    @Override
    public Integer createUser(UserRequest userRequest) {
        return userDao.createUser(userRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUserById(Integer userId) {
        userDao.deleteUserById(userId);
    }
}
