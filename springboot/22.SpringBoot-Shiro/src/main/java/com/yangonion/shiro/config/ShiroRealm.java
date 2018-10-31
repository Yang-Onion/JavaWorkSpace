package com.yangonion.shiro.config;

import com.yangonion.shiro.bean.ShiroPermission;
import com.yangonion.shiro.bean.ShiroRole;
import com.yangonion.shiro.bean.ShiroUser;
import com.yangonion.shiro.mapper.ShiroRolePermissionMapper;
import com.yangonion.shiro.mapper.ShiroUserMapper;
import com.yangonion.shiro.mapper.ShiroUserRoleMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private ShiroUserMapper shiroUserMapper;

    @Autowired
    private ShiroUserRoleMapper shiroUserRoleMapper;

    @Autowired
    private ShiroRolePermissionMapper shiroRolePermissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        Object obj =SecurityUtils.getSubject().getPrincipal();
        if ( null==obj){
           return null;
        }
        ShiroUser shiroUser= shiroUserMapper.queryUserByName(obj.toString());

        System.out.println("用户" + shiroUser.getName() + "获取权限-----ShiroRealm.doGetAuthorizationInfo");

        //获取角色
        SimpleAuthorizationInfo simpleAuthorizationInfo =new SimpleAuthorizationInfo();
        List<ShiroRole> roleList = shiroUserRoleMapper.findByUserName(shiroUser.getName());
        Set<String> roleSet = new HashSet<>();

        for (ShiroRole r :roleList){
            roleSet.add(r.getName());
        }
        //设置角色
        simpleAuthorizationInfo.setRoles(roleSet);

        //获取权限
        List<ShiroPermission> permissionList=shiroRolePermissionMapper.findByUserName(shiroUser.getName());
        Set<String> permissionSet = new HashSet<>();
        for (ShiroPermission permission:permissionList){
            permissionSet.add(permission.getName());
        }
        //设置权限
        simpleAuthorizationInfo.setStringPermissions(permissionSet);

        return  simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        String password = new String((char[])token.getCredentials());

        ShiroUser user = shiroUserMapper.queryUserByName(username);
        if (user==null){
            throw new UnknownAccountException("账号不存在");
        }
        if (!password.equalsIgnoreCase(user.getPassword())){
            throw  new IncorrectCredentialsException("账号或密码错误");
        }
        if (!user.getStatus().equalsIgnoreCase("0")){
            throw  new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(username,password,user.getName());
        return simpleAuthenticationInfo;
    }
}
