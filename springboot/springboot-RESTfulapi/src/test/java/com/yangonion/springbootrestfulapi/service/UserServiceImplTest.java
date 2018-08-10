package com.yangonion.springbootrestfulapi.service;

import com.sun.glass.ui.Application;
import com.yangonion.springbootrestfulapi.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Before
    public  void setUp(){
        userService.removeAllUsers();
    }


    @Test
    public  void testUserService(){

        addUser();

        getUserList();

        getUser();

        removeUser();

        removeAllUsers();

    }



   private void addUser() {
        User user = new User();
        user.setName("Yang-Onion");
        user.setAge(20);

        User user2 = new User();
        user2.setName("Yang@Onion");
        user2.setAge(30);

        userService.addUser(user);
        userService.addUser(user2);
    }


    private void getUserList() {
        List<User> userList = userService.getUserList();
        Assert.assertEquals(2,userList.size());
    }


    private void getUser() {
        User user = userService.getUser(1L);
        Assert.assertEquals(20,user.getAge().intValue());
    }


    private void removeUser() {
        userService.removeUser(2L);
        Assert.assertEquals(1,userService.getUserList().size());
    }

    private void removeAllUsers() {
        userService.removeAllUsers();
        Assert.assertEquals(0,userService.getUserList().size());
    }

}