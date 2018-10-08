package com.yangonion.test;

import com.yangonion.test.bean.User;
import com.yangonion.test.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public  void  queryUserByIdTest(){
        User user = userService.queryUserById(1);
        Assert.assertEquals("用户名不相同","Yang-Onion",user.getName());
    }

    @Test
    @Transactional
    public void addUserTest(){
        User user = new User();
        user.setName("Sam-Zhang-3");
        user.setAge(23);

        userService.add(user);
        System.out.println("通过事务测试插入数据,测试结束后，数据库中不会有这条垃圾数据(但是,这个auto increatement 的id是已经被用了的)！");
    }
}
