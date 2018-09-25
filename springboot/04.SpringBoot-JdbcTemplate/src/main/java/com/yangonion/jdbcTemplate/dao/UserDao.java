package com.yangonion.jdbcTemplate.dao;

import com.yangonion.jdbcTemplate.bean.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

public interface UserDao {

    int add(User user);
    int update(User user);
    int deleteById(Long id);
    List<Map<String,Object>> queryUserListMap();
    User queryUserById(Long id);
}
