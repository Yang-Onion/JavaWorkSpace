package com.yangonion.mybatis.service;

import com.yangonion.mybatis.bean.User;

public interface UserService {
    int add(User user);
    int delete(long id);
    int update(User user);
    User queryUserById(long id);

}
