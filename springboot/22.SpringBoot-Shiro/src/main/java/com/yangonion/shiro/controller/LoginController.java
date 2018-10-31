package com.yangonion.shiro.controller;

import com.yangonion.shiro.bean.ResponseResult;
import com.yangonion.shiro.bean.ShiroUser;
import com.yangonion.shiro.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public  String login(){
        return  "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(String name,String password,Boolean rememberMe){
        ResponseResult responseResult=new ResponseResult();
        password= MD5Utils.encrypt(name,password);
        UsernamePasswordToken token = new UsernamePasswordToken(name,password,rememberMe);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
            responseResult=ResponseResult.ok();
        }catch (UnknownAccountException ex){
            responseResult=ResponseResult.error(ex.getMessage());
        }catch (IncorrectCredentialsException ex){
            responseResult=ResponseResult.error(ex.getMessage());
        }
        catch (LockedAccountException ex){
            responseResult=ResponseResult.error(ex.getMessage());
        }
        catch (AuthenticationException ex) {
            responseResult=ResponseResult.error(ex.getMessage());
        }
        return responseResult;
    }

    @RequestMapping("/")
    public String redirectIndex(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model){
        Object obj =  SecurityUtils.getSubject().getPrincipal();
        ShiroUser user=new ShiroUser();
        user.setName(obj.toString());
        model.addAttribute("user",user);
        return "index";
    }

    @GetMapping("/403")
    public String forbid() {
        return "403";
    }
}
