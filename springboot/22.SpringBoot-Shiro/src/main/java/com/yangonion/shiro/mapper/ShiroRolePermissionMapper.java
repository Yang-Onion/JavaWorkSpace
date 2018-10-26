package com.yangonion.shiro.mapper;

import com.yangonion.shiro.bean.ShiroPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ShiroRolePermissionMapper {

    @Select("select p.id,p.url,p.name from shirorole r   left join shirouserrole ur on(r.id = ur.roleid) left join shirouser u on(u.id = ur.userid)  left join shirorolepermisssion rp on(rp.roleid = r.id)  left join shiropermission p on(p.id = rp.permessionid )  where u.name = #{name} ")
    List<ShiroPermission> findByUserName(String name);
}
