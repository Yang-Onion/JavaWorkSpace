package com.yangonion.filterandinterceptor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/index")
    public  String index(){
        return "Index";
    }
}
