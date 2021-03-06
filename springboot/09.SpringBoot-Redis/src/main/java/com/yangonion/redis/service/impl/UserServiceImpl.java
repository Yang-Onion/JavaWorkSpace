package com.yangonion.redis.service.impl;

import com.yangonion.redis.bean.User;
import com.yangonion.redis.mapper.UserMapper;
import com.yangonion.redis.service.UserService;
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
    public User update(User user) {
         userMapper.update(user);
         return  queryUserById(user.getId());
    }

    @Override
    public User queryUserById(long id) {
        return userMapper.queryUserById(id);
    }
}
