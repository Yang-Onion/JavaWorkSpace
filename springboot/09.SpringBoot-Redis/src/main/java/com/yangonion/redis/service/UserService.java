package com.yangonion.redis.service;

import com.yangonion.redis.bean.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "user")
public interface UserService {
    int add(User user);
    @CacheEvict(key = "#p0",allEntries = true)
    int delete(long id);
    @CachePut(key = "#p0.id")
    User update(User user);
    @Cacheable(key = "#p0")
    User queryUserById(long id);

}
