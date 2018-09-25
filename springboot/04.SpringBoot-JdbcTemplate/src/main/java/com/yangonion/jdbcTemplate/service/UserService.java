package com.yangonion.jdbcTemplate.service;

import com.yangonion.jdbcTemplate.bean.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    int add(User user);
    int update(User user);
    int deleteById(Long id);
    List<Map<String,Object>> queryUserListMap();
    User queryUserById(Long id);
}
