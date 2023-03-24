package com.example.school.Dao;

import com.example.school.Dto.UserQueryParams;
import com.example.school.Dto.UserRequest;
import com.example.school.Model.User;
import com.example.school.RowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
    public Integer countUser(String search) {

        String sql = "SELECT count(*) FROM User";
        if (search != null && !search.isEmpty()) {
            sql += " WHERE name LIKE '%" + search + "%'";
        }
        Map<String, Object> map = new HashMap<>();
        return namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
    }

    @Override
    public List<User> getUsers(UserQueryParams userQueryParams) {
        String sql = "SELECT id, name, tel, email, gender FROM User WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        sql = addFilteringSql(sql, map, userQueryParams);

        //  分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", userQueryParams.getLimit());
        map.put("offset", userQueryParams.getOffset());

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        return userList;
    }

    @Override
    public Integer createUser(UserRequest userRequest) {
        String sql = "INSERT INTO User(name, tel, email, gender) VALUES(:name, :tel, :email, :gender)";

        Map<String, Object> map = new HashMap<>();
        map.put("name", userRequest.getName());
        map.put("tel", userRequest.getTel());
        map.put("email", userRequest.getEmail());
        map.put("gender", userRequest.getGender());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer userId = keyHolder.getKey().intValue();
        return userId;
    }

    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT id, name, tel, email, gender FROM User WHERE id=:id";

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

        String sql = "UPDATE User SET name=:name, tel=:tel, email=:email, gender=:gender WHERE id=:id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getUserId());
        map.put("name", user.getName());
        map.put("tel", user.getTel());
        map.put("email", user.getEmail());
        map.put("gender", user.getGender());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteUserById(Integer userId) {
        String sql = "DELETE FROM User WHERE id=:userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    private String addFilteringSql(String sql, Map<String, Object> map, UserQueryParams userQueryParams){

        //  查詢條件
        if(userQueryParams.getSearch() != null){
            sql = sql+ " AND name LIKE :search";
            map.put("search", "%"+userQueryParams.getSearch()+"%");
        }
        return sql;
    }

}
