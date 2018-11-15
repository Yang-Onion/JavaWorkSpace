package com.yangonion.eurekaclient.controller;

import com.yangonion.eurekaclient.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public User get(@PathVariable Long id){
        log.info("获取用户id为"+id+"的信息");
        return  new User(id,"Yang-Onion","123456");
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<User> get(){
        log.info("获取所有用户的信息");
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L,"Yang-Onion","123abc"));
        userList.add(new User(2L,"Tomcat","123abc"));
        return  userList;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void add(@RequestBody User user){
        log.info("添加用户成功"+user);
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    public void update(@RequestBody User user){
        log.info("修改用户成功"+user);
    }

    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        log.info("删除用户id为"+id+"的信息成功！");
    }
}
