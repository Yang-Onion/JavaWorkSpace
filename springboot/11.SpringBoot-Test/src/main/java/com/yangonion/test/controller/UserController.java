package com.yangonion.test.controller;

import com.yangonion.test.bean.User;
import com.yangonion.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    int add(){
        User user = new User();
        user.setName("Yang-Onion");
        user.setAge(20);
        return  userService.add(user);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    int delete(long id){
        return  userService.delete(id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    int update(long id){
        User user = userService.queryUserById(id);
        user.setName(user.getName()+"-Modify");
        user.setAge(user.getAge()+10);
        return  userService.update(user);
    }


    @RequestMapping(value = "/queryUser",method = RequestMethod.GET)
    User queryUserById(long id){
        return userService.queryUserById(id);
    }
}
