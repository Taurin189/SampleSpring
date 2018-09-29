package com.spring.sample.controller;

import com.spring.sample.entity.ToDoEntity;
//import com.spring.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class AuthController {
//    @Autowired
//    private UserService userService;

    @GetMapping("/login")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("login.html");
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView post(
            ModelAndView mav,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        mav.setViewName("login.html");
        return mav;
    }
}
