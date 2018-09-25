package com.yangonion.jdbcTemplate.service.impl;

import com.yangonion.jdbcTemplate.bean.User;
import com.yangonion.jdbcTemplate.dao.UserDao;
import com.yangonion.jdbcTemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int add(User user) {
        return userDao.add(user);
    }

    @Override
    public int update(User user) {
        return  userDao.update(user);
    }

    @Override
    public int deleteById(Long id) {
        return  userDao.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> queryUserListMap() {
        return  userDao.queryUserListMap();
    }

    @Override
    public User queryUserById(Long id) {
        return userDao.queryUserById(id);
    }
}
