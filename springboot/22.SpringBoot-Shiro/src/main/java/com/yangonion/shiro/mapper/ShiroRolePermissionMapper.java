package com.yangonion.shiro.mapper;

import com.yangonion.shiro.bean.ShiroPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShiroRolePermissionMapper {

    List<ShiroPermission> findByUserName(String name);
}