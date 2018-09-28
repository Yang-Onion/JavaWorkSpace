package com.yangonion.mybatis.multidatasource.service.impl;

import com.yangonion.mybatis.multidatasource.mysqldao.UserMapper;
import com.yangonion.mybatis.multidatasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<Long, Object>> getAllUser() {
        return userMapper.getAllUser();
    }
}
