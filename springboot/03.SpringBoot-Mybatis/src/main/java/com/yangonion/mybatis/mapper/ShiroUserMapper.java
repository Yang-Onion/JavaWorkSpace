package com.yangonion.mybatis.mapper;

import com.yangonion.mybatis.bean.ShiroUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ShiroUserMapper {
    ShiroUser queryUserByName(String name);
}
