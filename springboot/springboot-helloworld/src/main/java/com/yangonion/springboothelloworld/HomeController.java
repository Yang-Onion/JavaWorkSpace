package com.yangonion.springboothelloworld;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/index")
    public  String index(ModelMap map){
        //加入一个属性，用来在模板中读取
        //类似于c#中的ViewBag.Account="Yang-Onion"
        map.addAttribute("Account","Yang-Onion");

        // return模板文件的名称，对应src/main/resources/templates/index.html
        //类似于 c# 中的 return View("index");
        return "index";
    }
}
