package com.yangonion.shiro.mapper;

import com.yangonion.shiro.bean.ShiroRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ShiroUserRoleMapper {

    @Select("select r.id, r.name, r.memo from shirorole r left join shirouserrole ur on ( r.id = ur.roleid ) left join shirouser u on ( u.id = ur.userid ) where u.name =#{name}")

    List<ShiroRole> findByUserName(String name);
}
