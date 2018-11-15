package com.yangonion.serverconsumer.controller;

import com.yangonion.serverconsumer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/info")
    public String getInfo(){
        return  this.restTemplate.getForEntity("http://Server-Provider/info",String.class).getBody();
    }

    @RequestMapping(value = "user/{id:\\d+}",method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        Map<String,Long> params = new HashMap<>();
        params.put("id",1L);

        URI uri = UriComponentsBuilder.fromUriString("http://Server-Provider/user/{id}")
                .build().expand(params).encode().toUri();
        return  this.restTemplate.getForEntity(uri, User.class).getBody();
        //return  this.restTemplate.getForEntity("http://Server-Provider/user/{id}",User.class,id).getBody();
    }

    @RequestMapping(value = "user",method = RequestMethod.GET)
    public List<User> getUser(){
        return this.restTemplate.getForObject("http://Server-Provider/user",List.class);
    }

    @RequestMapping(value = "user/add",method = RequestMethod.POST)
    public String addUser() {
        User user = new User(1L, "mrbird", "123456");
        HttpStatus status = this.restTemplate.postForEntity("http://Server-Provider/user", user, null).getStatusCode();
        if (status.is2xxSuccessful()) {
            return "新增用户成功";
        } else {
            return "新增用户失败";
        }
    }
    @RequestMapping(value = "user/update",method = RequestMethod.PUT)
    public void updateUser() {
        User user = new User(1L, "mrbird", "123456");
        this.restTemplate.put("http://Server-Provider/user", user);
    }
    @RequestMapping(value = "user/delete/{id:\\d+}",method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
        this.restTemplate.delete("http://Server-Provider/user/{1}", id);
    }
}
