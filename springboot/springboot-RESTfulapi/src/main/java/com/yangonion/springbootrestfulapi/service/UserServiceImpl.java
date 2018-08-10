package com.yangonion.springbootrestfulapi.service;

import com.yangonion.springbootrestfulapi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Yang
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user) {

        jdbcTemplate.update("insert into user(name,age) value (?,?)",user.getName(),user.getAge());
    }

    @Override
    public void removeUser(Long id) {
        jdbcTemplate.update("delete from user where id=?",id);
    }

    @Override
    public void removeAllUsers() {
        jdbcTemplate.update("delete from user");
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update("update user set name=?,age=? where id=?",user.getName(),user.getAge(),user.getId());
    }

    @Override
    public List<User> getUserList() {

        //jdbcTemplate.query("select * from user", new RowMapperResultSetExtractor(UserRowMapper));
        List<User> userList = new ArrayList<User>();;
        List list= jdbcTemplate.queryForList("select * from user ");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Map userMap = (Map) iterator.next();

            User user = new User();
            user.setId((Long) userMap.get("id"));
            user.setName((String) userMap.get("name"));
            user.setAge((Integer) userMap.get("age"));

            userList.add(user);
        }

        return  userList;
    }

    @Override
    public User getUser(Long id) {
        User user = new User();
        jdbcTemplate.query("select * from user where id=?",
                new Object[]{id},
                new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
            }
        });
        return user;
    }
}



