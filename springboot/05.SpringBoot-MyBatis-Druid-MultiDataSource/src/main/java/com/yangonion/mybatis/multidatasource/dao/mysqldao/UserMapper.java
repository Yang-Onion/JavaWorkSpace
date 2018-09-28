package com.yangonion.mybatis.multidatasource.dao.mysqldao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    List<Map<Long,Object>> getAllUser();
}
