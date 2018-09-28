package com.yangonion.aop.log.controller;

import com.yangonion.aop.log.annotation.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Log("访问index")
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public void index(String message){

    }

    @Log("访问home")
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public void home(String message) throws InterruptedException{
        Thread.sleep(2000);
    }


    @Log("访问base")
    @RequestMapping(value = "/base",method = RequestMethod.GET)
    public void base(String name, String age){

    }
}
