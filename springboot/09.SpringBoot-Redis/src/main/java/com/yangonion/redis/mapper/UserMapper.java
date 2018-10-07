package com.yangonion.redis.mapper;

import com.yangonion.redis.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user(name,age) values(#{name},#{age})")
    int add(User user);

    @Delete("delete from user where id=#{id}")
    int delete(long id);

    @Update("update user set name=#{name},age=#{age} where id=#{id}")
    int update(User user);

    @Select("select * from user where id=#{id}")
    @Results(id="user",value={
            @Result(property = "id",column = "id",javaType = long.class),
            @Result(property = "name",column = "name",javaType = String.class),
            @Result(property = "age",column = "age",javaType = int.class)
    })
    User queryUserById(long id);

}
