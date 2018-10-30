package com.yangonion.mybatis.controller;

import com.yangonion.mybatis.bean.ShiroUser;
import com.yangonion.mybatis.mapper.ShiroUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroUserController {

    @Autowired
    private ShiroUserMapper shiroUserMapper;

    @RequestMapping("/index")
    public String index(){
        try {
            ShiroUser shiroUser=  shiroUserMapper.queryUserByName("yangonion");
            return shiroUser.getName()+"---"+shiroUser.getCreatetime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
