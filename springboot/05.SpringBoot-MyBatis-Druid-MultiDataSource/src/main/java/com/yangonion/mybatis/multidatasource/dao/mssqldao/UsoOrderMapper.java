package com.yangonion.mybatis.multidatasource.dao.mssqldao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsoOrderMapper {
    List<Map<Integer,Object>> getAllUsoOrder();
}
