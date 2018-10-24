package com.yangonion.shiro.mapper;

import com.yangonion.shiro.bean.ShiroUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ShiroUserMapper {
      ShiroUser queryUserByName(String name);
}
