package com.yangonion.springbootrestfulapi.api;
import com.yangonion.springbootrestfulapi.domain.User;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
public class UserController {

    static Map<Long, User>  userMap = Collections.synchronizedMap(new HashMap<Long,User>());

    @RequestMapping(value = "/",method= RequestMethod.GET)
    public List<User> getUserList(){
        List<User> userList = new ArrayList<User>(userMap.values());
        return  userList;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        User user = userMap.get(id);
        return  user;
    }

    @RequestMapping(name = "/",method=RequestMethod.POST)
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

    @RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
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

    @RequestMapping(value = "/",method=RequestMethod.PUT)
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
