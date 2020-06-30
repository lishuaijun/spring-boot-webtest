package com.offcn.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class login {

    @PostMapping(value = "/user/login")
    public String loginController(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  Map<String,Object> map, HttpSession session){
        if (username != null
                && username.length() != 0
                && "123".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名或密码错误");
            return "login";
        }


    }
}
