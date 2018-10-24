package com.yangonion.shiro.config;

import com.yangonion.shiro.bean.ShiroUser;
import com.yangonion.shiro.mapper.ShiroUserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private ShiroUserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        String password = new String((char[])token.getCredentials());

        ShiroUser user = userMapper.queryUserByName(username);
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
