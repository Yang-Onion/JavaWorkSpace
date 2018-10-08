package com.yangonion.test.service;

import com.yangonion.test.bean.User;

public interface UserService {
    int add(User user);
    int delete(long id);
    int update(User user);
    User queryUserById(long id);

}
