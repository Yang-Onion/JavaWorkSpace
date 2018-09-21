package com.yangonion.config.controller;

import com.yangonion.config.model.GithubBean;
import com.yangonion.config.model.GithubProperty;
import com.yangonion.config.model.PersonalBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private GithubProperty githubProperty;

    @Autowired
    private GithubBean githubBean;

    @Autowired
    private PersonalBean personalBean;

    @RequestMapping("/")
    String index(){

        return  githubProperty.toString();
    }
    @RequestMapping("/home")
    String home(){
        return  githubBean.toString();
    }

    @RequestMapping("/base")
    String base(){
        return  personalBean.toString();
    }
}
