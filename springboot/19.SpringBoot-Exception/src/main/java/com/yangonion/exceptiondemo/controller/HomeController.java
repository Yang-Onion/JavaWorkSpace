package com.yangonion.exceptiondemo.controller;

import com.yangonion.exceptiondemo.exceptions.DomainException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {

    @RequestMapping("/index")
    public  void index(){
        throw  new DomainException("1");
    }
}
