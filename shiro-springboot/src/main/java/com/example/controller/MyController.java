package com.example.controller;

import com.example.Service.UserService;
import com.example.Service.UserServiceImpl;
import com.example.pojo.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;

@Controller
public class MyController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hellp,shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String addUser(Model model){
        return "/user/add";
    }

    @RequestMapping("/user/update")
    public String updateUser(Model model){
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);//执行登录方法，如果没有异常就说明ok了
            return "index";
        } catch (UnknownAccountException e) {//用户名不存在
            model.addAttribute("msg","用户名密码错误");
            return "login";
        }catch (IncorrectCredentialsException e) {//密码不存在
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }

    @RequestMapping("/test")
    @ResponseBody
    public SysUser test(String userName){
        userName = "root";
        return userService.queryUserByName(userName);
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String noauth(){
        return "未授权页面请授权";
    }
}
