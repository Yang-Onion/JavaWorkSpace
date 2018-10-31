package com.yangonion.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

/*
// 表示当前Subject已经通过login进行了身份验证；即Subject.isAuthenticated()返回true。
@RequiresAuthentication

// 表示当前Subject已经身份验证或者通过记住我登录的。
@RequiresUser
// 表示当前Subject没有身份验证或通过记住我登录过，即是游客身份。
@RequiresGuest
// 表示当前Subject需要角色admin和user。
@RequiresRoles(value={"admin", "user"}, logical= Logical.AND)
// 表示当前Subject需要权限user:a或user:b。
@RequiresPermissions (value={"user:a", "user:b"}, logical= Logical.OR)
*
*
* */
    @RequiresPermissions("user:user")
    @RequestMapping("list")
    public String userList(Model model){
        model.addAttribute("value","获取用户信息");
        return "user";
    }

    @RequiresPermissions("user:add")
    @RequestMapping("add")
    public String userAdd(Model model) {
        model.addAttribute("value", "新增用户");
        return "user";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping("delete")
    public String userDelete(Model model) {
        model.addAttribute("value", "删除用户");
        return "user";
    }
}
