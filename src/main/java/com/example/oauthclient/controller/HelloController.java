package com.example.oauthclient.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public DefaultOAuth2User hello(){
        System.out.println("hello");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       return (DefaultOAuth2User) authentication.getPrincipal();
    }
    @RequestMapping("/haha")
    public String haha(){
        System.out.println("haha");
        return "haha,成功了！1!1";
    }
    @RequestMapping("/form")
    public String form(){
        System.out.println("haha");
        return "form,成功了！1!1";
    }

}
