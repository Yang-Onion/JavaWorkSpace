package com.yangonion.shiro.mapper;

import com.yangonion.shiro.bean.ShiroRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShiroUserRoleMapper {
    List<ShiroRole> findByUserName(String name);
}