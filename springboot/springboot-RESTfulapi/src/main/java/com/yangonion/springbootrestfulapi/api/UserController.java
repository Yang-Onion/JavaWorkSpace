package com.yangonion.springbootrestfulapi.api;
import com.yangonion.springbootrestfulapi.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
public class UserController {

    static Map<Long, User>  userMap = Collections.synchronizedMap(new HashMap<Long,User>());

    @ApiOperation(value = "获取用户列表",notes = "")
    @RequestMapping(value = {""},method= RequestMethod.GET)
    public List<User> getUserList(){
        List<User> userList = new ArrayList<User>(userMap.values());
        return  userList;
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        User user = userMap.get(id);
        return  user;
    }

    @ApiOperation(value = "创建用户",notes = "根据user创建用户")
    @ApiImplicitParam(name = "user",value = "用户详细实体user",required = true,dataType = "User")
    @RequestMapping(name = "",method=RequestMethod.POST)
    public  Long addUser(@ModelAttribute User user){
        Long userId =null;
        if (null == user){
            throw new IllegalArgumentException("参数不能为空!");
        }
        if (userMap.containsKey(user.getId())){
            userId=user.getId();
        }
        else{
            userMap.put(user.getId(),user);
            userId=user.getId();
        }
        return userId;
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public  String RemoveUser(@ModelAttribute Long id){
        String result = "";
        User user = userMap.get(id);
        if (null == user){
            throw  new Error("用户未找到,不能删除!");
        }
        else{
            userMap.remove(id);
            result="Remove Sucess!";
        }
        return  result;
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public  String UpdateUser(@PathVariable Long id, @ModelAttribute User user){
        String result = "";
        User tempUser = userMap.get(id);
        if (null == user){
            throw  new Error("用户未找到,不能更新!");
        }
        else{
            tempUser.setName(user.getName());
            tempUser.setAge(user.getAge());
            userMap.put(id,tempUser);
            result="Update Sucess!";
        }
        return  result;
    }

}
