package com.yangonion.serverconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/info")
    public String getInfo(){
        return  this.restTemplate.getForEntity("http://Server-Provider/info",String.class).getBody();
    }
}
