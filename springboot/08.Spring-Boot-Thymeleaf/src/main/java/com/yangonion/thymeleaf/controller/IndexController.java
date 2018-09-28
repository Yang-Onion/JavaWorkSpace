package com.yangonion.thymeleaf.controller;

import com.yangonion.thymeleaf.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(Model model){
        List<User> list = new ArrayList<User>();
        list.add(new User(1,"Yang-Onion"));
        list.add(new User(2,"Lee"));
        list.add(new User(3,"Tom"));
        model.addAttribute("userlist",list);
        return "userlist";
    }
}
