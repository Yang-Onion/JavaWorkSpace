package com.yangonion.swagger.controller;

import com.yangonion.swagger.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "HomeController",tags = {"用户操作接口"})
@RequestMapping("home")
public class HomeController {

   @ApiIgnore
   @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public   String hello(){
        return  "hello";
    }
    @ApiOperation(value = "获取用户信息",notes = "根据id获取用户信息",tags = {"查询"})
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Long",paramType = "path")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  User getUserById(@PathVariable(value = "id") Long id){
        User user = new User();
        user.setId(id);
        user.setAge(20);
        user.setName("Yang-Onion");
        return  user;
    }

    @ApiOperation(value = "获取用户列表",notes = "获取所有用户",tags = {"查询"})
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> getUserList(){
        User user = new User();
        user.setId(1);
        user.setAge(20);
        user.setName("Yang-Onion");

        User user2 = new User();
        user2.setId(2);
        user2.setAge(20);
        user2.setName("Yang-Onion");

        List<User> userList = new ArrayList<User>();
        userList.add(user);
        userList.add(user2);

        return  userList;
    }

    @ApiOperation(value = "新增用户",notes = "新增用户",tags = {"更新"})
    @ApiImplicitParam(name = "user",value = "用户实例",dataType = "User",required = true)
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Map<String,Boolean> addUser(@RequestBody User user){
        Map<String,Boolean> map = new HashMap<>();
        map.put("result",true);
        return map;
    }

    @ApiOperation(value = "删除用户",notes = "删除用户",tags = {"删除"})
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Long")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Map<String,Boolean> deleteUser(@PathVariable(value = "id") Long id){
        Map<String,Boolean> map = new HashMap<>();
        map.put("result",true);
        return map;
    }

    @ApiOperation(value = "更新用户",notes = "更新用户",tags = {"更新"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Long"),
            @ApiImplicitParam(name = "user",value = "用户实体",required = true,dataType = "User")

    })
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Map<String,Boolean> updateUser(@PathVariable(value = "id") Long id,@RequestBody User user){
        Map<String,Boolean> map = new HashMap<>();
        map.put("result",true);
        return map;
    }




}
