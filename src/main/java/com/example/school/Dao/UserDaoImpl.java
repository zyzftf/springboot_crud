package com.example.school.Dao;

import com.example.school.Dto.UserRequest;
import com.example.school.Model.User;
import com.example.school.RowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<User> getUsers() {
        String sql = "SELECT id, name, tel FROM User";

        Map<String, Object> map = new HashMap<>();

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        return userList;
    }

    @Override
    public Integer createUser(UserRequest userRequest) {
        String sql = "INSERT INTO User(name, tel) VALUES(:name, :tel)";

        Map<String, Object> map = new HashMap<>();
        map.put("name", userRequest.getName());
        map.put("tel", userRequest.getTel());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer userId = keyHolder.getKey().intValue();
        return userId;
    }

    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT id, name, tel FROM User WHERE id=:id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(userList.size() > 0){
            return userList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void updateUser(User user) {

        String sql = "UPDATE User SET name=:name, tel=:tel WHERE id=:id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getUserId());
        map.put("name", user.getName());
        map.put("tel", user.getTel());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteUserById(Integer userId) {
        String sql = "DELETE FROM User WHERE id=:userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}