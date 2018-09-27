package com.yangonion.mybatis.service.impl;

import com.yangonion.mybatis.bean.User;
import com.yangonion.mybatis.mapper.UserMapper;
import com.yangonion.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public int delete(long id) {
        return userMapper.delete(id);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User queryUserById(long id) {
        return userMapper.queryUserById(id);
    }
}
