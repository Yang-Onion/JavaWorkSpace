package com.yangonion.jdbcTemplate.controller;

import com.yangonion.jdbcTemplate.bean.User;
import com.yangonion.jdbcTemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import  java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public int add(String name,int age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return  this.userService.add(user);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public  int delete(Long id){
        return  this.userService.deleteById(id);
    }

    @RequestMapping(value = "/queryUserList",method = RequestMethod.GET)
    public List<Map<String,Object>> queryUserList(){
        return  this.userService.queryUserListMap();
    }

    @RequestMapping(value = "/queryUserById",method = RequestMethod.GET)
    public User queryUserById(Long id){
        return  this.userService.queryUserById(id);
    }

}
