package com.example.school.RowMapper;

import com.example.school.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        User user = new User();
        user.setUserId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setTel(resultSet.getString("tel"));
        user.setEmail(resultSet.getString("email"));
        user.setGender(resultSet.getString("gender"));

        return user;
    }
}
