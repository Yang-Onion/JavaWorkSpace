package com.yangonion.mybatis.mapper;

import com.yangonion.mybatis.bean.ShiroUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ShiroUserMapper {

/*
      @Select("select * from shirouser where name=#{name}")
      @Results(id="user",value={
              @Result(property = "id",column = "id",javaType = long.class),
              @Result(property = "name",column = "name",javaType = String.class),
              @Result(property = "password",column = "password",javaType = String.class),
              @Result(property = "crateTime",column = "createtime",javaType = Date.class),
              @Result(property = "status",column = "status",javaType = String.class),
      })
      */

      ShiroUser queryUserByName(String name);
}
