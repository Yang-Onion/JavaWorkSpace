package com.yangonion.mybatis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/")
    String index(){
        return "03.SpringBoot-Mybatis";
    }
}
